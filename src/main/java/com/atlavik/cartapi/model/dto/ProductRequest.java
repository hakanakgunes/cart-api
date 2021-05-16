package com.atlavik.cartapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    @NonNull
    private String description;
    @NonNull
    private String category;
    @NonNull
    private BigDecimal price;
}
