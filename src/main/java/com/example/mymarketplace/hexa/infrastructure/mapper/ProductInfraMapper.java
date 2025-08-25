package com.example.mymarketplace.hexa.infrastructure.mapper;

import com.example.mymarketplace.hexa.domain.model.Product;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductInfraMapper {
    ProductInfraMapper INSTANCE = Mappers.getMapper(ProductInfraMapper.class);

    Product entityToDomain(ProductEntity product);
}
