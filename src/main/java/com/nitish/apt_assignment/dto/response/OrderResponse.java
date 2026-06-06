package com.nitish.apt_assignment.dto.response;

import com.nitish.apt_assignment.model.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse
        (
                UUID orderId,
                String customerName,
                String productName,
                Status status,
                LocalDateTime updatedAt
        ) { }
