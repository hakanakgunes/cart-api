package com.atlavik.cartapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -686839079368696501L;

    @Type(type = "pg-uuid")
    private UUID id;
    private String description;
    private String category;
    private BigDecimal price;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Product(String description, String category, BigDecimal price){
        this.id = UUID.randomUUID();
        this.description = description;
        this.category = category;
        this.price = price;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
}
