package com.atlavik.cartapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {
    @NonNull
    String countryCode;
    @NonNull
    String currency;
}
