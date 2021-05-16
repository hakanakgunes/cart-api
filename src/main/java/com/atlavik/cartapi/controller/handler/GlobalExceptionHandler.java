package com.atlavik.cartapi.controller.handler;

import com.atlavik.cartapi.exception.CartNotFoundException;
import com.atlavik.cartapi.model.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiError messageNotReadableException(Exception ex) {
        return new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {CartNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ApiError noEventException(CartNotFoundException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = {NoResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ApiError noEventException(NoResultException ex) {
        return new ApiError(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    }
}
