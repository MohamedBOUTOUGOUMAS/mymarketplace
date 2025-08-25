package com.example.mymarketplace.hexa.domain.port.primary;

import com.example.mymarketplace.hexa.domain.exception.*;
import com.example.mymarketplace.hexa.domain.model.Basket;
import com.example.mymarketplace.hexa.domain.model.BasketItem;

import java.math.BigInteger;
import java.util.List;

public interface BasketPort {

    BigInteger calculateTotal(String clientId) throws BasketNotFoundException, ClientYearTurnoverNotFoundException;

    Basket createBasket(List<BasketItem> basketItems, String clientId) throws ClientNotFoundException, ProductNotFoundException, BasketAlreadyExistsException;

    Basket updateBasket(String basketId, List<BasketItem> basketItemsToAdd, List<BasketItem> basketItemsToRemove, String clientId) throws AccessDeniedException, BasketNotFoundException, ProductNotFoundException;

    Basket findByBasketItemId(String basketItemId) throws BasketNotFoundException;

    void deleteBasket(String basketId) throws BasketNotFoundException;
}
