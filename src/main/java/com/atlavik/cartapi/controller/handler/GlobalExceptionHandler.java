package com.atlavik.cartapi.controller.handler;

import com.atlavik.cartapi.exception.CartNotFoundException;
import com.atlavik.cartapi.exception.ProductException;
import com.atlavik.cartapi.model.dto.ApiError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CartNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ApiError cartNotFoundException(CartNotFoundException ex) {
        log.error("Cart Not found: " + ex.getLocalizedMessage());
        return new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = {ProductException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ApiError productException(ProductException ex) {
        log.error("Product not found: " + ex.getLocalizedMessage());
        return new ApiError(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiError generalServerException(Exception ex) {
        log.error(ex.getLocalizedMessage());
        return new ApiError("Please Contact with system administrator", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
