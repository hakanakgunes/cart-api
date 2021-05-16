package com.atlavik.cartapi.service;

import com.atlavik.cartapi.mapper.CartResponseMapper;
import com.atlavik.cartapi.mapper.CartResponseMapperImpl;
import com.atlavik.cartapi.model.Cart;
import com.atlavik.cartapi.model.Product;
import com.atlavik.cartapi.repository.CartRepository;
import com.atlavik.cartapi.service.impl.CartServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CartServiceImpl.class, CartResponseMapperImpl.class})
public class CartServiceTest {
    @Autowired
    CartService cartService;
    @Autowired
    CartResponseMapper cartMapper;
    @MockBean
    CartRepository cartRepository;

    private final UUID cartId = UUID.randomUUID();
    private UUID productId;

    @SneakyThrows
    @BeforeEach
    public void init() {
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart("USA", "US");
        Product product = new Product("Test product", "TEST CATEGORY", BigDecimal.ZERO);
        cart.setProducts(new ArrayList<>(Collections.singletonList(product)));
        carts.add(cart);
        productId = product.getId();

        when(cartRepository.findAll()).thenReturn(carts);
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart));
        when(cartRepository.getProduct(any(), any())).thenReturn(product);
    }

    @SneakyThrows
    @Test
    public void testGetCart() {
        assertNotNull(cartService.getCart(UUID.randomUUID()));
    }

    @SneakyThrows
    @Test
    public void testGetCarts() {
        assertNotNull(cartService.getCarts().get(0));
    }

    @SneakyThrows
    @Test
    public void testCreateCart() {
        doAnswer(invocation -> {
                    Cart cart = (Cart) invocation.getArguments()[0];

                    assertEquals(cart.getCountryCode(), "USA");
                    assertEquals(cart.getCurrency(), "US");
                    return null;
                }
        ).when(cartRepository).save(any());


        cartService.createCart("USA", "US");
    }

    @SneakyThrows
    @Test
    public void testGetProducts() {
        assertNotNull(cartService.getProducts(cartId).get(0));
    }

    @SneakyThrows
    @Test
    public void testGetProduct() {
        assertNotNull(cartService.getProduct(cartId, productId));
    }

    @SneakyThrows
    @Test
    public void testAddProduct() {
        doAnswer(invocation -> {
                    Cart cart = (Cart) invocation.getArguments()[0];
                    assertNotNull(cart.getProducts().get(0));
                    return null;
                }
        ).when(cartRepository).save(any());

        cartService.addProduct(cartId, "Test", "Test CATEGORY", BigDecimal.ZERO);
    }

    @SneakyThrows
    @Test
    public void testDeleteProduct() {
        doAnswer(invocation -> {
                    Cart cart = (Cart) invocation.getArguments()[0];
                    assertTrue(cart.getProducts().size() == 0);
                    return null;
                }
        ).when(cartRepository).save(any());

        cartService.deleteProduct(cartId, productId);
    }

}
