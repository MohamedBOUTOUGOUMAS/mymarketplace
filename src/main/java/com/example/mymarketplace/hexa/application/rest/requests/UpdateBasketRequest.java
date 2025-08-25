package com.example.mymarketplace.hexa.application.rest.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class UpdateBasketRequest {
    @NotBlank
    private String clientId;

    private List<BasketItemRequest> basketItemsToAdd;

    private List<BasketItemRequest> basketItemsToRemove;
}
