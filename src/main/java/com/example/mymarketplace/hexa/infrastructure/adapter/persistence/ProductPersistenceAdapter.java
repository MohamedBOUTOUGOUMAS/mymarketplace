package com.example.mymarketplace.hexa.infrastructure.adapter.persistence;

import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Product;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.ProductPersistencePort;
import com.example.mymarketplace.hexa.infrastructure.mapper.ProductInfraMapper;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.ProductJpaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {
    @Autowired
    private ProductJpaRepository jpaRepository;


    @Override
    public Product findById(String id) throws ProductNotFoundException {
        return ProductInfraMapper.INSTANCE.entityToDomain(
                jpaRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }
}
