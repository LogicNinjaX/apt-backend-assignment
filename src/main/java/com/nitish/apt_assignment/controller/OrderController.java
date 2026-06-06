package com.nitish.apt_assignment.controller;

import com.nitish.apt_assignment.dto.request.OrderCreateRequest;
import com.nitish.apt_assignment.dto.request.OrderUpdateRequest;
import com.nitish.apt_assignment.dto.response.ApiResponse;
import com.nitish.apt_assignment.dto.response.OrderResponse;
import com.nitish.apt_assignment.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderCreateRequest request){
        var response = orderService.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "Order created"));
    }

    @GetMapping(path = "/{orderId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<OrderResponse>> getOrder(@PathVariable UUID orderId){
        var response = orderService.getOrder(orderId);

        return ResponseEntity.ok(ApiResponse.success(response, "Details fetched successfully"));
    }

    @PatchMapping(path = "/{orderId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable UUID orderId, @RequestBody OrderUpdateRequest request){
        var response = orderService.updateOrder(orderId, request);

        return ResponseEntity.ok(ApiResponse.success(response, "Details updated successfully"));
    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable UUID orderId){
        orderService.deleteOrder(orderId);

        return ResponseEntity.ok(ApiResponse.success("Order deleted successfully"));
    }
}
