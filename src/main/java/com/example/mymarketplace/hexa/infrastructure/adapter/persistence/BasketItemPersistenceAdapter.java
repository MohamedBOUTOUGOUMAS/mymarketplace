package com.example.mymarketplace.hexa.infrastructure.adapter.persistence;

import com.example.mymarketplace.hexa.domain.exception.BasketItemNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.port.primary.BasketPort;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.BasketItemPersistencePort;
import com.example.mymarketplace.hexa.infrastructure.mapper.BasketItemInfraMapper;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.BasketItemJpaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemPersistenceAdapter implements BasketItemPersistencePort {

    @Autowired
    private BasketItemJpaRepository basketItemJpaRepository;

    @Autowired
    private BasketPort basketPort;

    @Override
    @Transactional
    public BasketItem create(String basketId, BasketItem basketItem) throws BasketNotFoundException {
        var basketItemEntity = BasketItemInfraMapper.INSTANCE.domainToEntity(basketItem);
        basketItemEntity.setBasket(new BasketEntity(basketId));
        var basketItemCreated = basketItemJpaRepository.save(basketItemEntity);
        return BasketItemInfraMapper.INSTANCE.entityToDomain(basketItemCreated);
    }

    @Override
    @Transactional
    public void update(String basketItemId, Integer quantity) throws BasketItemNotFoundException {
        var item = basketItemJpaRepository.findById(basketItemId).orElseThrow(BasketItemNotFoundException::new);
        item.setQuantity(quantity);
        var basketItemUpdated = basketItemJpaRepository.save(item);
        BasketItemInfraMapper.INSTANCE.entityToDomain(basketItemUpdated);
    }

    @Override
    @Transactional
    public void delete(String id) throws BasketNotFoundException {
        var basket = basketPort.findByBasketItemId(id);
        if (basket.getBasketItems().size() <= 1) {
            basketPort.deleteBasket(basket.getId());
        } else {
            basketItemJpaRepository.deleteById(id);
        }
    }
}
