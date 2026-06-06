package com.nitish.apt_assignment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;


@Schema(description = "Standard error response")
public record ErrorResponse(

        @Schema(
                description = "Indicates whether the request was successful",
                example = "false"
        )
        boolean success,

        @Schema(
                description = "HTTP status code",
                example = "400"
        )
        int status,

        @Schema(
                description = "Error message",
                example = "Validation failed"
        )
        String message,

        @Schema(
                description = "Field level validation errors"
        )
        Map<String, String> errors,

        @Schema(
                description = "API path",
                example = "/api/v1/orders"
        )
        String path,

        @Schema(
                description = "Error timestamp",
                example = "2026-06-06T20:30:45"
        )
        LocalDateTime timestamp
) {
    public ErrorResponse(HttpStatus status, String message, Map<String, String> errors, String path) {
        this(false, status.value(), message, errors, path, LocalDateTime.now());
    }
}