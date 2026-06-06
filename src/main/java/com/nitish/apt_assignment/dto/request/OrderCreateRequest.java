package com.nitish.apt_assignment.dto.request;

import com.nitish.apt_assignment.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderCreateRequest
        (
                @NotBlank(message = "Customer name is required")
                @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
                String customerName,

                @NotBlank(message = "Product name is required")
                @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
                String productName,

                @NotNull(message = "Status is required")
                Status status
        ) {
}
