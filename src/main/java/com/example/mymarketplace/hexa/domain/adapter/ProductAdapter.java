package com.example.mymarketplace.hexa.domain.adapter;

import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Product;
import com.example.mymarketplace.hexa.domain.port.primary.ProductPort;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.ProductPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProductAdapter implements ProductPort {
    @Autowired
    private ProductPersistencePort productPersistencePort;


    @Override
    public Product findById(String id) throws ProductNotFoundException {
        return productPersistencePort.findById(id);
    }
}
