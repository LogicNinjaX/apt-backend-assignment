package com.nitish.apt_assignment.dto.request;

import com.nitish.apt_assignment.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for creating an order")
public record OrderCreateRequest
        (

                @Schema(
                        description = "Customer name",
                        example = "Nitish Sahni",
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
                @NotBlank(message = "Customer name is required")
                @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
                String customerName,


                @Schema(
                        description = "Product name",
                        example = "MacBook Pro",
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
                @NotBlank(message = "Product name is required")
                @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
                String productName,


                @Schema(
                        description = "Order status",
                        example = "PENDING",
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
                @NotNull(message = "Status is required")
                Status status
        ) {
}
