package com.example.mymarketplace.hexa.infrastructure.persistence.repositories;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketItemJpaRepository extends JpaRepository<BasketItemEntity, String> {
}
