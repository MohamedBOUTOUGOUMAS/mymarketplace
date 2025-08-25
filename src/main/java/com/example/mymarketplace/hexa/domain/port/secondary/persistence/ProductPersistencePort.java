package com.example.mymarketplace.hexa.domain.port.secondary.persistence;

import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Product;

public interface ProductPersistencePort {
    Product findById(String name) throws ProductNotFoundException;
}
