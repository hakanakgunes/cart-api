package com.atlavik.cartapi.repository;

import com.atlavik.cartapi.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CartRepositoryCustom {
    Product getProduct(@Param("cartId") UUID cartId, @Param("productId") UUID productId) throws JsonProcessingException;
}
