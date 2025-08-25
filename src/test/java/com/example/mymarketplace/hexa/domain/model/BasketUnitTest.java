package com.example.mymarketplace.hexa.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketUnitTest {

    @ParameterizedTest
    @CsvSource({
            "PARTICULAR, Iphone 16, PHONE_HIGH_END, 5, 1500, 1150, 1000, 7500",
            "PARTICULAR, Nokia 3310, PHONE_MID_RANGE, 5, 800, 600, 550, 4000",
            "PARTICULAR, MacBook, LAPTOP, 5, 1200, 1000, 900, 6000",
    })
    void should_calculate_correctly_for_particular(Client.ClientTypeEnum clientType, String productName,
                                                   Product.CategoryEnum productCategory,
                                                   Integer quantity,
                                                   Integer priceForParticular,
                                                   Integer priceForProfessional,
                                                   Integer priceForProfessionalAfterDiscount,
                                                   BigInteger expectedTotal) throws Exception {

        var client = new Client();
        client.setId("clientId1");
        client.setFirstName("Jhon");
        client.setLastName("doe");
        client.setType(clientType);

        var product = new Product();
        product.setId("productId1");
        product.setName(productName);
        product.setCategory(productCategory);
        product.setPriceForParticular(priceForParticular);
        product.setPriceForProfessional(priceForProfessional);
        product.setPriceForProfessionalAfterDiscount(priceForProfessionalAfterDiscount);

        var basket = new Basket();
        basket.setClient(client);
        var item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        basket.setBasketItems(List.of(item));

        assertEquals(expectedTotal, basket.calculateTotal());
    }

    @ParameterizedTest
    @CsvSource({
            "PROFESSIONAL, 1000000000, Iphone 16, PHONE_HIGH_END, 5, 1500, 1150, 1000, 5000",
            "PROFESSIONAL, 1000000000, Nokia 3310, PHONE_MID_RANGE, 5, 800, 600, 550, 2750",
            "PROFESSIONAL, 1000000000, MacBook, LAPTOP, 5, 1200, 1000, 900, 4500",
            "PROFESSIONAL, 1000000, MacBook, LAPTOP, 5, 1200, 1000, 900, 5000",
    })
    void should_calculate_correctly_for_professional(Client.ClientTypeEnum clientType, Integer amount, String productName,
                                                     Product.CategoryEnum productCategory,
                                                     Integer quantity,
                                                     Integer priceForParticular,
                                                     Integer priceForProfessional,
                                                     Integer priceForProfessionalAfterDiscount,
                                                     BigInteger expectedTotal) throws Exception {
        var client = new Client();
        client.setId("clientId1");
        client.setSiret("7654890123");
        client.setTva("20");
        client.setTurnovers(List.of(new Client.Turnover("2024", amount)));
        client.setType(clientType);
        client.setEnableDiscountMaxAmount(10000000);

        var product = new Product();
        product.setId("productId1");
        product.setName(productName);
        product.setCategory(productCategory);
        product.setPriceForParticular(priceForParticular);
        product.setPriceForProfessional(priceForProfessional);
        product.setPriceForProfessionalAfterDiscount(priceForProfessionalAfterDiscount);

        var basket = new Basket();
        basket.setClient(client);
        var item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        basket.setBasketItems(List.of(item));

        assertEquals(expectedTotal, basket.calculateTotal());
    }
}