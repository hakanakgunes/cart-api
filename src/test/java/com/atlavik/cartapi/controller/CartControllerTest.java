package com.atlavik.cartapi.controller;

import com.atlavik.cartapi.model.dto.CartRequest;
import com.atlavik.cartapi.model.dto.CartResponse;
import com.atlavik.cartapi.model.dto.ProductRequest;
import com.atlavik.cartapi.model.dto.ProductResponse;
import com.atlavik.cartapi.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CartService cartService;

    @SneakyThrows
    @BeforeAll
    public void init() {
        List<CartResponse> carts = new ArrayList<>();
        CartResponse mockCart = new CartResponse(UUID.randomUUID(), "USA", "US", new ArrayList<>(), LocalDateTime.now(), null);
        carts.add(mockCart);
        List<ProductResponse> products = new ArrayList<>();
        ProductResponse mockProduct = new ProductResponse(UUID.randomUUID(), "Test product", "TEST CATEGORY", BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now());
        products.add(mockProduct);

        when(cartService.getCarts()).thenReturn(carts);
        when(cartService.getCart(any())).thenReturn(mockCart);
        when(cartService.getProducts(any())).thenReturn(products);
        when(cartService.getProduct(any(), any())).thenReturn(mockProduct);
    }

    @SneakyThrows
    @Test
    public void testGetCarts() {
        MockHttpServletResponse response = mockMvc.perform(get("/api/carts")).andExpect(status().isOk()).andReturn().getResponse();
        assertNotNull(response.toString());
    }

    @SneakyThrows
    @Test
    public void testGetCart() {
        MockHttpServletResponse response = mockMvc.perform(get("/api/carts")
                .param("cartId", UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.toString());
    }

    @SneakyThrows
    @Test
    public void testCreateCart() {
        doAnswer(invocation -> {
                    String countryCode = (String) invocation.getArguments()[0];
                    String currency = (String) invocation.getArguments()[1];
                    assertNotNull(countryCode);
                    assertNotNull(currency);
                    return null;
                }
        ).when(cartService).createCart(anyString(), anyString());

        CartRequest cartRequest = new CartRequest();
        cartRequest.setCountryCode("USA");
        cartRequest.setCurrency("US");

        mockMvc.perform(post("/api/carts").param("cartId", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.convertObjectToString(cartRequest)))
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    public void testGetProducts() {
        String uri = String.format("/api/carts/%s/products", UUID.randomUUID());
        MockHttpServletResponse response = mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.toString());
    }

    @SneakyThrows
    @Test
    public void testGetProduct() {
        String uri = String.format("/api/carts/%1$s/products/%2$s", UUID.randomUUID(), UUID.randomUUID());

        MockHttpServletResponse response = mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.toString());
    }

    @SneakyThrows
    @Test
    public void testAddProduct() {
        doAnswer(invocation -> {
                    UUID productId = (UUID) invocation.getArguments()[0];
                    String description = (String) invocation.getArguments()[1];
                    String category = (String) invocation.getArguments()[2];
                    BigDecimal price = (BigDecimal) invocation.getArguments()[3];
                    assertNotNull(productId);
                    assertNotNull(description);
                    assertNotNull(category);
                    assertNotNull(price);
                    return null;
                }
        ).when(cartService).addProduct(any(), anyString(), anyString(), any());

        ProductRequest productRequest = new ProductRequest();
        productRequest.setDescription("Test");
        productRequest.setCategory("Test CATEGORY");
        productRequest.setPrice(BigDecimal.ZERO);
        String uri = String.format("/api/carts/%s/products", UUID.randomUUID());

        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(this.convertObjectToString(productRequest)))
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    public void testDeleteProduct() {
        doAnswer(invocation -> {
                    UUID cartId = (UUID) invocation.getArguments()[0];
                    UUID productId = (UUID) invocation.getArguments()[1];

                    assertNotNull(cartId);
                    assertNotNull(productId);
                    return null;
                }
        ).when(cartService).deleteProduct(any(), any());

        String uri = String.format("/api/carts/%1$s/products/%2$s", UUID.randomUUID(), UUID.randomUUID());

        mockMvc.perform(delete(uri)).andExpect(status().isNoContent());
    }

    private String convertObjectToString(Object obj) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(obj);
    }
}
