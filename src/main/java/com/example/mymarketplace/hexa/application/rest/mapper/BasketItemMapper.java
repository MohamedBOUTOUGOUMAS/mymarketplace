package com.example.mymarketplace.hexa.application.rest.mapper;

import com.example.mymarketplace.hexa.application.rest.requests.BasketItemRequest;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketItemMapper {
    BasketItemMapper INSTANCE = Mappers.getMapper(BasketItemMapper.class);

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "product.name", source = "productName")
    @Mapping(target = "product.category", source = "productCategory")
    BasketItem requestToDomain(BasketItemRequest request);
}
