package com.atlavik.cartapi.repository.impl;

import com.atlavik.cartapi.exception.ProductException;
import com.atlavik.cartapi.model.Product;
import com.atlavik.cartapi.repository.CartRepositoryCustom;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.query.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Product getProduct(@Param("cartId") UUID cartId, @Param("productId") UUID productId) throws ProductException {
        try {
            JsonNode product = (JsonNode) em.createNativeQuery(
                    "select product from cart, jsonb_array_elements(products) product where id= :cartId and product->>'id' = :productId")
                    .unwrap(NativeQuery.class)
                    .setParameter("cartId", cartId)
                    .setParameter("productId", productId.toString())
                    .addScalar("product", JsonNodeBinaryType.INSTANCE)
                    .getSingleResult();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.treeToValue(product, Product.class);
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }
    }
}
