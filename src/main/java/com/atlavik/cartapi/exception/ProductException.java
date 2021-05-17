package com.atlavik.cartapi.exception;

import lombok.Getter;

@Getter
public class ProductException extends  RuntimeException{

    private static final long serialVersionUID = 3289374590947230213L;

    public ProductException(String message){
        super(message);
    }
}
