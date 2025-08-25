package com.example.mymarketplace.hexa.application.rest.requests;

import com.example.mymarketplace.hexa.domain.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasketItemRequest {
    private String Id;
    @NotBlank private String productId;
    @NotBlank private String productName;
    @NotBlank private Product.CategoryEnum productCategory;
    @NotBlank @Min(1) private Integer quantity;
    private String basketId;

    public BasketItemRequest(String productId, String productName, Product.CategoryEnum productCategory, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.quantity = quantity;
    }

    public BasketItemRequest(String id, String productId, String productName, Product.CategoryEnum productCategory, Integer quantity) {
        this.Id = id;
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.quantity = quantity;
    }
}
