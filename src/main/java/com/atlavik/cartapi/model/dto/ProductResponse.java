package com.atlavik.cartapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse{

    private UUID id;
    private String description;
    private String category;
    private BigDecimal price;
    private LocalDateTime created;
    private LocalDateTime updated;
}
