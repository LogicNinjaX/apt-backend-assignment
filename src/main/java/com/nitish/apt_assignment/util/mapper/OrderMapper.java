package com.nitish.apt_assignment.util.mapper;

import com.nitish.apt_assignment.dto.request.OrderCreateRequest;
import com.nitish.apt_assignment.dto.response.OrderResponse;
import com.nitish.apt_assignment.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    Order toOrder(OrderCreateRequest createRequest);

    OrderResponse toResponse(Order order);
}
