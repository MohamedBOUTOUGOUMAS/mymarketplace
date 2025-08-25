package com.example.mymarketplace.hexa.domain.port.primary;

import com.example.mymarketplace.hexa.domain.exception.BasketItemNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.model.BasketItem;

public interface BasketItemPort {

    BasketItem create(String basketId, BasketItem basketItem) throws BasketNotFoundException, ProductNotFoundException;

    void update(String basketItemId, Integer quantity) throws BasketItemNotFoundException;

    void delete(String id) throws BasketItemNotFoundException, BasketNotFoundException;
}
