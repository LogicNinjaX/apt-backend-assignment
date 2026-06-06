package com.nitish.apt_assignment.dto.response;

import com.nitish.apt_assignment.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Order details")
public record OrderResponse
        (

                @Schema(
                        description = "Unique order identifier",
                        example = "550e8400-e29b-41d4-a716-446655440000"
                )
                UUID orderId,

                @Schema(example = "Nitish Sahni")
                String customerName,

                @Schema(example = "MacBook Pro")
                String productName,

                @Schema(example = "PENDING")
                Status status,

                @Schema(
                        description = "Last update timestamp",
                        example = "2026-06-06T20:00:00"
                )
                LocalDateTime updatedAt
        ) { }
