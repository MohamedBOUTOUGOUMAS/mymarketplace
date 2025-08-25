package com.example.mymarketplace.hexa.application.rest.controller;

import com.example.mymarketplace.hexa.application.rest.requests.BasketItemRequest;
import com.example.mymarketplace.hexa.application.rest.requests.UpdateBasketItemRequest;
import com.example.mymarketplace.hexa.domain.model.Product;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.BasketItemJpaRepository;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.BasketJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.mymarketplace.hexa.application.rest.controller.ClientControllerTest.asJsonString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/sql/data.sql"})
class BasketItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BasketItemJpaRepository basketItemJpaRepository;

    @Autowired
    private BasketJpaRepository basketJpaRepository;

    @Test
    void should_add_item_to_the_existing_basket() throws Exception {
        var basketItemToAdd = new BasketItemRequest();
        basketItemToAdd.setBasketId("basketId1");
        basketItemToAdd.setProductId("productId3");
        basketItemToAdd.setProductName("Iphone 16 pro");
        basketItemToAdd.setProductCategory(Product.CategoryEnum.PHONE_HIGH_END);
        basketItemToAdd.setQuantity(5);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/basketItems")
                        .content(asJsonString(basketItemToAdd))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.basketItems[0].productName")
                        .value("Iphone 16"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.basketItems[1].productName")
                        .value("Iphone 16 pro"));
    }

    @Test
    void should_update_basketItem_quantity_successfully() throws Exception {
        var basketItemId = "item1";
        var quantity = 100;
        var request = new UpdateBasketItemRequest();
        request.setQuantity(quantity);

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/basketItems/"+basketItemId)
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.basketItems[0].quantity")
                        .value("100"));
    }

    @Test
    void should_delete_item_from_the_existing_basket() throws Exception {
        var basketItemId = "item1";
        var basketId = "basketId1";

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/basketItems/"+basketItemId))
                .andExpect(status().isNoContent());

        var deletedItem = basketItemJpaRepository.findById(basketItemId);
        assertFalse(deletedItem.isPresent());

        var deletedBasket = basketJpaRepository.findById(basketId);
        Assertions.assertFalse(deletedBasket.isPresent());
    }
}