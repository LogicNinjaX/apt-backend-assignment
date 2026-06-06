package com.nitish.apt_assignment.service.impl;

import com.nitish.apt_assignment.dto.request.OrderCreateRequest;
import com.nitish.apt_assignment.dto.request.OrderUpdateRequest;
import com.nitish.apt_assignment.dto.response.NotificationResponse;
import com.nitish.apt_assignment.dto.response.OrderResponse;
import com.nitish.apt_assignment.exception.OrderNotFoundException;
import com.nitish.apt_assignment.model.Order;
import com.nitish.apt_assignment.repository.OrderRepository;
import com.nitish.apt_assignment.service.NotificationService;
import com.nitish.apt_assignment.service.OrderService;
import com.nitish.apt_assignment.util.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final NotificationService notificationService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.notificationService = notificationService;
    }

    @Transactional
    @Override
    public OrderResponse createOrder(OrderCreateRequest request){
        Order order = orderMapper.toOrder(request);
        order = orderRepository.saveAndFlush(order);

        logger.info("Order details saved successfully [order id={}]", order.getOrderId());
        OrderResponse response = orderMapper.toResponse(order);
        notificationService.sendOrderUpdate(new NotificationResponse<>("Order Created", response));
        return response;
    }

    @Override
    public OrderResponse getOrder(UUID orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id="+orderId));

        logger.info("Order details fetched successfully [order id={}]", orderId);
        return orderMapper.toResponse(order);
    }

    @Transactional
    @Override
    public OrderResponse updateOrder(UUID orderId, OrderUpdateRequest request){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id="+orderId));

        if (Objects.nonNull(request.productName())){
            order.setProductName(request.productName());
        }

        if (Objects.nonNull(request.customerName())){
            order.setCustomerName(request.customerName());
        }

        if (Objects.nonNull(request.status())){
            order.setStatus(request.status());
        }

        order = orderRepository.save(order);

        logger.info("Order details updated successfully [order id={}]", orderId);
        OrderResponse response = orderMapper.toResponse(order);
        notificationService.sendOrderUpdate(new NotificationResponse<>("Order Updated", response));
        return response;
    }

    @Transactional
    @Override
    public void deleteOrder(UUID orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id="+orderId));

        orderRepository.delete(order);
        logger.info("Order deleted successfully [order id={}]", orderId);
        notificationService
                .sendOrderUpdate(new NotificationResponse<>("Order Deleted", orderMapper.toResponse(order)));
    }

}
