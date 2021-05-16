package com.atlavik.cartapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private UUID id;
    private String countryCode;
    private String currency;
    private List<ProductResponse> products;
    private LocalDateTime created;
    private LocalDateTime updated;
}
