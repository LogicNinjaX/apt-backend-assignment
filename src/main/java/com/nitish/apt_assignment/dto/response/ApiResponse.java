package com.nitish.apt_assignment.dto.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final LocalDateTime timeStamp;

    ApiResponse(Builder<T> builder){
        this.success = builder.success;
        this.message = builder.message;
        this.data = builder.data;
        this.timeStamp = builder.timeStamp;
    }

    public static class Builder<T>{
        private boolean success;
        private String message;
        private T data;
        private LocalDateTime timeStamp;

        public Builder<T> success(boolean success){
            this.success = success;
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder<T> timeStamp(LocalDateTime timeStamp){
            this.timeStamp = timeStamp;
            return this;
        }

        public ApiResponse<T> build(){
            return new ApiResponse<>(this);
        }
    }


    public static <T> ApiResponse<T> success(T data, String message){
        return new Builder<T>()
                .success(true)
                .message(message)
                .data(data)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> success(String message){
        return new Builder<T>()
                .success(true)
                .message(message)
                .timeStamp(LocalDateTime.now())
                .build();
    }
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
