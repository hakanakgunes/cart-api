package com.atlavik.cartapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;
    private final int statusCode;
    private final String message;

    public ApiError(String message, int statusCode) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.statusCode = statusCode;
    }
}
