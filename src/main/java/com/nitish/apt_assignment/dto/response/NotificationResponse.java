package com.nitish.apt_assignment.dto.response;

public record NotificationResponse<T>
        (
                String message,
                T data
        ) { }
