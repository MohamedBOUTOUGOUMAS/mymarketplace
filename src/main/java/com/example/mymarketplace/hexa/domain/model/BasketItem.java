package com.example.mymarketplace.hexa.domain.model;

import lombok.Data;

@Data
public class BasketItem {
    private String id;
    private Product product;
    private Integer quantity;
}
