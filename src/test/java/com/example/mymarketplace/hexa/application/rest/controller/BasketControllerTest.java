package com.example.mymarketplace.hexa.application.rest.controller;

import com.example.mymarketplace.hexa.application.rest.requests.BasketItemRequest;
import com.example.mymarketplace.hexa.application.rest.requests.CreateBasketRequest;
import com.example.mymarketplace.hexa.application.rest.requests.UpdateBasketRequest;
import com.example.mymarketplace.hexa.domain.model.Product;
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

import java.util.List;

import static com.example.mymarketplace.hexa.application.rest.controller.ClientControllerTest.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/sql/data.sql"})
class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BasketJpaRepository basketJpaRepository;

    @Test
    void should_create_basket_successfully() throws Exception {
        var basketToCreate = new CreateBasketRequest();
        basketToCreate.setClientId("clientId2");
        var p1 = new BasketItemRequest("productId1", "Iphone 16", Product.CategoryEnum.PHONE_HIGH_END, 5);
        basketToCreate.setBasketItems(List.of(p1));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/baskets")
                        .content(asJsonString(basketToCreate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        var createdBasket = basketJpaRepository.findByClientId("clientId2");
        Assertions.assertTrue(createdBasket.isPresent());
    }

    @Test
    void should_fail_to_create_basket_for_same_client() throws Exception {
        var basketToCreate = new CreateBasketRequest();
        basketToCreate.setClientId("clientId1");
        var p1 = new BasketItemRequest("productId1", "Iphone 16", Product.CategoryEnum.PHONE_HIGH_END, 5);
        basketToCreate.setBasketItems(List.of(p1));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/baskets")
                        .content(asJsonString(basketToCreate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_update_basket_successfully() throws Exception {
        var basketId = "basketId1";

        var basketToUpdate = new UpdateBasketRequest();
        basketToUpdate.setClientId("clientId1");

        var productToAdd = new BasketItemRequest("productId2", "Nokia 3310", Product.CategoryEnum.PHONE_MID_RANGE, 5);
        var productToRemove = new BasketItemRequest("item1","productId1", "Iphone 16", Product.CategoryEnum.PHONE_HIGH_END, 5);

        basketToUpdate.setBasketItemsToAdd(List.of(productToAdd));
        basketToUpdate.setBasketItemsToRemove(List.of(productToRemove));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/baskets/"+basketId)
                        .content(asJsonString(basketToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.basketItems[0].productName")
                        .value("Nokia 3310"));
    }

    @Test
    void should_delete_basket_successfully() throws Exception {
        var basketId = "basketId1";
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/baskets/"+basketId))
                .andExpect(status().isNoContent());
        var deletedBasket = basketJpaRepository.findById(basketId);
        Assertions.assertFalse(deletedBasket.isPresent());
    }

    @Test
    void should_calculate_total_basket_successfully() throws Exception {
        var clientId = "clientId1";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/baskets/total-to-pay?clientId="+clientId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total")
                        .value(7500));
    }
}