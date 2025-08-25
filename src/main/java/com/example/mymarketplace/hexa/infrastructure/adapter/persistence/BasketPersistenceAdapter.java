package com.example.mymarketplace.hexa.infrastructure.adapter.persistence;

import com.example.mymarketplace.hexa.domain.exception.AccessDeniedException;
import com.example.mymarketplace.hexa.domain.exception.BasketAlreadyExistsException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Basket;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.model.Client;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.BasketPersistencePort;
import com.example.mymarketplace.hexa.infrastructure.mapper.BasketInfraMapper;
import com.example.mymarketplace.hexa.infrastructure.mapper.ClientInfraMapper;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.BasketItemJpaRepository;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.BasketJpaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class BasketPersistenceAdapter implements BasketPersistencePort {

    @Autowired
    private BasketJpaRepository basketJpaRepository;

    @Autowired
    private BasketItemJpaRepository basketItemJpaRepository;

    @Override
    @Transactional
    public Basket createBasket(List<BasketItem> items, Client client) throws BasketAlreadyExistsException {
        var basket = new BasketEntity();
        basket.setClient(client.getType() == Client.ClientTypeEnum.PROFESSIONAL ?
                ClientInfraMapper.INSTANCE.domainToProfessionalEntity(client) :
                ClientInfraMapper.INSTANCE.domainToParticularEntity(client)
        );
        var basketItems = items.stream().map(BasketInfraMapper.INSTANCE::domainToEntity).toList();
        basket.setBasketItems(basketItems);

        var existingBasket = basketJpaRepository.findByClientId(client.getId());
        if (existingBasket.isPresent()) { throw new BasketAlreadyExistsException(); }
        var createdBasket = basketJpaRepository.save(basket);
        return BasketInfraMapper.INSTANCE.entityToDomain(createdBasket);
    }

    @Override
    public Basket findByIdClientId(String clientId) throws BasketNotFoundException {
        return basketJpaRepository.findByClientId(clientId)
                .map(BasketInfraMapper.INSTANCE::entityToDomain).orElseThrow(BasketNotFoundException::new);
    }

    @Override
    public Basket findByBasketItemId(String basketItemId) throws BasketNotFoundException {
        return basketJpaRepository.findByBasketItemId(basketItemId)
                .map(BasketInfraMapper.INSTANCE::entityToDomain).orElseThrow(BasketNotFoundException::new);
    }

    @Override
    @Transactional
    public Basket updateBasket(String basketId, List<BasketItem> basketItemsToAdd, List<BasketItem> basketItemsToRemove, String clientId) throws BasketNotFoundException, AccessDeniedException {
        var basket = basketJpaRepository.findByClientId(clientId).orElseThrow(BasketNotFoundException::new);

        if (!basket.getId().equals(basketId)) {
            throw new AccessDeniedException("No allowed to update the provided basket");
        }

        var basketToUpdate = new BasketEntity();
        basketToUpdate.setId(basket.getId());
        basketToUpdate.setClient(basket.getClient());
        basketToUpdate.setBasketItems(basketItemsToAdd.stream().map(BasketInfraMapper.INSTANCE::domainToEntity).toList());

        var itemsToDelete = basketItemsToRemove.stream().map(BasketItem::getId).toList();
        basketItemJpaRepository.deleteAllById(itemsToDelete);

        var updatedBasket = basketJpaRepository.save(basketToUpdate);
        return BasketInfraMapper.INSTANCE.entityToDomain(updatedBasket);
    }

    @Override
    @Transactional
    public void deleteBasket(String basketId) throws BasketNotFoundException {
        var basket = basketJpaRepository.findById(basketId).orElseThrow(BasketNotFoundException::new);
        basketJpaRepository.delete(basket);
    }
}
