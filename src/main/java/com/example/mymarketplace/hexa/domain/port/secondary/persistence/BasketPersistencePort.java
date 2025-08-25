package com.example.mymarketplace.hexa.domain.port.secondary.persistence;

import com.example.mymarketplace.hexa.domain.exception.AccessDeniedException;
import com.example.mymarketplace.hexa.domain.exception.BasketAlreadyExistsException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Basket;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.model.Client;

import java.util.List;

public interface BasketPersistencePort {

    Basket createBasket(List<BasketItem> items, Client client) throws BasketAlreadyExistsException;

    Basket findByIdClientId(String clientId) throws BasketNotFoundException;

    Basket findByBasketItemId(String basketItemId) throws BasketNotFoundException;

    Basket updateBasket(String basketId, List<BasketItem> basketItemsToAdd, List<BasketItem> basketItemsToRemove, String clientId) throws BasketNotFoundException, AccessDeniedException;

    void deleteBasket(String basketId) throws BasketNotFoundException;
}
