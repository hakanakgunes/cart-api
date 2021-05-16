package com.atlavik.cartapi.repository;

import com.atlavik.cartapi.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID>, CartRepositoryCustom {
}
