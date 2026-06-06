package com.nitish.apt_assignment.dto.request;

import com.nitish.apt_assignment.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for updating an order")
public record OrderUpdateRequest
        (

                @Schema(example = "Updated Customer")
                String customerName,

                @Schema(example = "Updated Product")
                String productName,

                @Schema(example = "DELIVERED")
                Status status
        ) { }
