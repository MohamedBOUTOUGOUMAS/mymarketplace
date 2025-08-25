package com.example.mymarketplace.hexa.domain.model;

import lombok.Data;

@Data
public class Product {
    public enum CategoryEnum { PHONE_HIGH_END, PHONE_MID_RANGE, LAPTOP }
    private String Id;
    private String name;
    private CategoryEnum category;
    private Integer priceForParticular;
    private Integer priceForProfessional;
    private Integer priceForProfessionalAfterDiscount;
}
