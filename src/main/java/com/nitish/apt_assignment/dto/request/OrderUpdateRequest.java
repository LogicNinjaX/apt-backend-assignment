package com.nitish.apt_assignment.dto.request;

import com.nitish.apt_assignment.model.Status;

public record OrderUpdateRequest
        (
                String customerName,
                String productName,
                Status status
        ) { }
