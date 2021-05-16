package com.atlavik.cartapi.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class Cart {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @Column(name = "country_code",  nullable = false)
    String countryCode;

    @Column(name = "currency",  nullable = false)
    String currency;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    List<Product> products;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime created;

    @Column(name = "update_time")
    private LocalDateTime updated;

    public Cart(String countryCode, String currency){
        this.countryCode = countryCode;
        this.currency = currency;
        this.products = new ArrayList<>();
        this.created = LocalDateTime.now();
    }

    public void setUpdated(){
        this.updated = LocalDateTime.now();
    }

    public void setProducts(List<Product> products){
        this.products= products;
    }
}
