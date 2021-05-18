package com.atlavik.cartapi.service;

import com.atlavik.cartapi.exception.CartNotFoundException;
import com.atlavik.cartapi.exception.ProductException;
import com.atlavik.cartapi.model.dto.CartResponse;
import com.atlavik.cartapi.model.dto.ProductResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface  CartService {

    CartResponse getCart(UUID cartId) throws CartNotFoundException;
    List<CartResponse> getCarts() throws CartNotFoundException;
    void createCart(String countryCode, String currency);
    List<ProductResponse> getProducts(UUID cartId) throws CartNotFoundException;
    ProductResponse getProduct(UUID cartId, UUID productId) throws ProductException;
    void addProduct(UUID cartId, String description, String category, BigDecimal price) throws CartNotFoundException;
    void deleteProduct(UUID cartId, UUID productId) throws CartNotFoundException;
}
