package com.atlavik.cartapi.service.impl;

import com.atlavik.cartapi.exception.CartNotFoundException;
import com.atlavik.cartapi.mapper.CartResponseMapper;
import com.atlavik.cartapi.model.Cart;
import com.atlavik.cartapi.model.Product;
import com.atlavik.cartapi.model.dto.CartResponse;
import com.atlavik.cartapi.model.dto.ProductResponse;
import com.atlavik.cartapi.repository.CartRepository;
import com.atlavik.cartapi.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartResponseMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, CartResponseMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartResponse getCart(UUID cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart doesn't exist."));
        return cartMapper.cartToCartResponse(cart);
    }

    @Override
    public List<CartResponse> getCarts() throws CartNotFoundException {
        return cartMapper.cartsToCartsResponse(cartRepository.findAll());
    }

    @Override
    public void createCart(String countryCode, String currency) {
        cartRepository.save(new Cart(countryCode, currency));
    }

    @Override
    public List<ProductResponse> getProducts(UUID cartId) throws CartNotFoundException {
        return this.getCart(cartId).getProducts();
    }

    @Override
    public ProductResponse getProduct(UUID cartId, UUID productId) throws CartNotFoundException, JsonProcessingException {
        return cartMapper.productToProductResponse(cartRepository.getProduct(cartId, productId));
    }

    @Override
    public void addProduct(UUID cartId, String description, String category, BigDecimal price) throws CartNotFoundException {
        Cart cart = cartMapper.cartResponseToCart(this.getCart(cartId));
        cart.getProducts().add(new Product(description, category, price));
        cart.setUpdated();
        cartRepository.save(cart);
    }

    @Override
    public void deleteProduct(UUID cartId, UUID productId) throws CartNotFoundException {
        Cart cart = cartMapper.cartResponseToCart(this.getCart(cartId));
        List<Product> products = cart.getProducts().stream().filter(product -> !product.getId().equals(productId)).collect(Collectors.toList());
        cart.setProducts(products);
        cart.setUpdated();
        cartRepository.save(cart);
    }
}
