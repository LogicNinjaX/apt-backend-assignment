package com.nitish.apt_assignment.service;

import com.nitish.apt_assignment.dto.request.OrderCreateRequest;
import com.nitish.apt_assignment.dto.request.OrderUpdateRequest;
import com.nitish.apt_assignment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(OrderCreateRequest request);

    OrderResponse getOrder(UUID orderId);

    OrderResponse updateOrder(UUID orderId, OrderUpdateRequest request);

    void deleteOrder(UUID orderId);
}
