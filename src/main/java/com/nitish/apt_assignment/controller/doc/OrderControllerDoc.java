package com.nitish.apt_assignment.controller.doc;

import com.nitish.apt_assignment.dto.request.OrderCreateRequest;
import com.nitish.apt_assignment.dto.request.OrderUpdateRequest;
import com.nitish.apt_assignment.dto.response.ApiResponse;
import com.nitish.apt_assignment.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderControllerDoc {


    @Operation(
            summary = "Create Order",
            description = "Creates a new order and broadcasts the update through WebSocket"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Order created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed")
    })
    ResponseEntity<ApiResponse<OrderResponse>> createOrder(OrderCreateRequest request);


    @Operation(
            summary = "Get Order",
            description = "Fetch order details by order ID"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    ResponseEntity<ApiResponse<OrderResponse>> getOrder(
            @Parameter(
            description = "Order UUID",
            example = "550e8400-e29b-41d4-a716-446655440000"
            )
            UUID orderId);

    @Operation(
            summary = "Update Order",
            description = "Updates an existing order and broadcasts the change through WebSocket"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    ResponseEntity<ApiResponse<OrderResponse>> updateOrder(UUID orderId, OrderUpdateRequest request);


    @Operation(
            summary = "Delete Order",
            description = "Deletes an order and broadcasts the deletion event through WebSocket"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Order not found")
    })
    ResponseEntity<ApiResponse<Void>> deleteOrder(UUID orderId);
}
