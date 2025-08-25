package com.example.mymarketplace.hexa.domain.adapter;

import com.example.mymarketplace.hexa.domain.exception.*;
import com.example.mymarketplace.hexa.domain.model.Basket;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.port.primary.BasketPort;
import com.example.mymarketplace.hexa.domain.port.primary.ClientPort;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.BasketPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BasketAdapter implements BasketPort {
    @Autowired
    private BasketPersistencePort persistencePort;
    @Autowired
    private ClientPort clientPort;

    @Override
    public BigInteger calculateTotal(String clientId) throws BasketNotFoundException, ClientYearTurnoverNotFoundException {
        var basket = persistencePort.findByIdClientId(clientId);
        return basket.calculateTotal();
    }

    @Override
    public Basket createBasket(List<BasketItem> basketItems, String clientId) throws ClientNotFoundException, ProductNotFoundException, BasketAlreadyExistsException {
        var client = clientPort.findClientById(clientId);
        return persistencePort.createBasket(basketItems, client);
    }

    @Override
    public Basket updateBasket(String basketId, List<BasketItem> basketItemsToAdd, List<BasketItem> basketItemsToRemove, String clientId) throws AccessDeniedException, BasketNotFoundException, ProductNotFoundException {
        return persistencePort.updateBasket(basketId, basketItemsToAdd, basketItemsToRemove, clientId);
    }

    @Override
    public Basket findByBasketItemId(String basketItemId) throws BasketNotFoundException {
        return persistencePort.findByBasketItemId(basketItemId);
    }

    @Override
    public void deleteBasket(String basketId) throws BasketNotFoundException {
        persistencePort.deleteBasket(basketId);
    }
}
