package com.example.mymarketplace.hexa.application.rest.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBasketItemRequest {
    @NotNull
    @Min(1)
    private Integer quantity;
}
