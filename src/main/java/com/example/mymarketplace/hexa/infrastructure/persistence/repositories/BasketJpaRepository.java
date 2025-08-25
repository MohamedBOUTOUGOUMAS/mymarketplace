package com.example.mymarketplace.hexa.infrastructure.persistence.repositories;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketJpaRepository extends JpaRepository<BasketEntity, String> {
    Optional<BasketEntity> findByClientId(String clientId);

    @Query(value = """
           select b from BasketEntity b left join BasketItemEntity bi on bi.basket.id = b.id where bi.id = :basketItemId
           """)
    Optional<BasketEntity> findByBasketItemId(String basketItemId);
}
