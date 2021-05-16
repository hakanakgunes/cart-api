package com.atlavik.cartapi.controller;

import com.atlavik.cartapi.model.Cart;
import com.atlavik.cartapi.model.Product;
import com.atlavik.cartapi.model.dto.CartRequest;
import com.atlavik.cartapi.model.dto.CartResponse;
import com.atlavik.cartapi.model.dto.ProductRequest;
import com.atlavik.cartapi.model.dto.ProductResponse;
import com.atlavik.cartapi.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse> getCarts(){
        return this.cartService.getCarts();
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse getCart(@PathVariable UUID cartId){
        return this.cartService.getCart(cartId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createCart(@RequestBody CartRequest cartRequest){
        this.cartService.createCart(cartRequest.getCountryCode(), cartRequest.getCurrency());
    }

    @GetMapping("/{cartId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts(@PathVariable UUID cartId){
        return this.cartService.getProducts(cartId);
    }

    @GetMapping("/{cartId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable UUID cartId, @PathVariable UUID productId) throws JsonProcessingException {
        return this.cartService.getProduct(cartId, productId);
    }

    @PutMapping("/{cartId}/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProduct(@PathVariable UUID cartId, @RequestBody ProductRequest productRequest){
        this.cartService.addProduct(cartId, productRequest.getDescription(),productRequest.getCategory(), productRequest.getPrice());
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID cartId, @PathVariable UUID productId){
        this.cartService.deleteProduct(cartId, productId);
    }
}
