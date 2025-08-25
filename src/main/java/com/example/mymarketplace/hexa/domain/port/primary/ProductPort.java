package com.example.mymarketplace.hexa.domain.port.primary;

import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Product;

public interface ProductPort {
    Product findById(String id) throws ProductNotFoundException;
}
