package com.atlavik.cartapi.exception;

import lombok.Getter;

@Getter
public class CartNotFoundException  extends  RuntimeException{
    private static final long serialVersionUID = -7965482974470586993L;

    public CartNotFoundException(String message){
        super(message);
    }
}
