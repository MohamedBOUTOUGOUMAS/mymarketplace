package com.example.mymarketplace.hexa.domain.port.secondary.persistence;

import com.example.mymarketplace.hexa.domain.exception.BasketItemNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.model.BasketItem;

public interface BasketItemPersistencePort {

    BasketItem create(String basketId, BasketItem basketItem) throws BasketNotFoundException;

    void update(String basketItemId, Integer quantity) throws BasketItemNotFoundException;

    void delete(String id) throws BasketItemNotFoundException, BasketNotFoundException;
}
