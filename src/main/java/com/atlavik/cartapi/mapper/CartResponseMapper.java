package com.atlavik.cartapi.mapper;

import com.atlavik.cartapi.model.Cart;
import com.atlavik.cartapi.model.Product;
import com.atlavik.cartapi.model.dto.CartResponse;
import com.atlavik.cartapi.model.dto.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CartResponseMapper {

    CartResponse cartToCartResponse(Cart cart);
    Cart cartResponseToCart(CartResponse cartResponse);
    List<CartResponse> cartsToCartsResponse(List<Cart> carts);
    ProductResponse productToProductResponse(Product product);
    Product productResponseToProduct(ProductResponse productResponse);
}
