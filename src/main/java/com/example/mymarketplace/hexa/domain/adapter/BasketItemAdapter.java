package com.example.mymarketplace.hexa.domain.adapter;

import com.example.mymarketplace.hexa.domain.exception.BasketItemNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.port.primary.BasketItemPort;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.BasketItemPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemAdapter implements BasketItemPort {
    @Autowired
    private BasketItemPersistencePort basketItemPersistencePort;

    @Override
    public BasketItem create(String basketId, BasketItem basketItem) throws BasketNotFoundException, ProductNotFoundException {
        return basketItemPersistencePort.create(basketId, basketItem);
    }

    @Override
    public void update(String basketItemId, Integer quantity) throws BasketItemNotFoundException {
        basketItemPersistencePort.update(basketItemId, quantity);
    }

    @Override
    public void delete(String id) throws BasketItemNotFoundException, BasketNotFoundException {
        basketItemPersistencePort.delete(id);
    }
}
