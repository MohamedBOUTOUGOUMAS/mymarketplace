package com.example.mymarketplace.hexa.infrastructure.persistence.repositories;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {
}
