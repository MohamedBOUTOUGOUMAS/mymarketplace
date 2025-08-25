package com.example.mymarketplace.hexa.infrastructure.mapper;

import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketItemInfraMapper {

    BasketItemInfraMapper INSTANCE = Mappers.getMapper(BasketItemInfraMapper.class);

    BasketItem entityToDomain(BasketItemEntity basketItemEntity);
    BasketItemEntity domainToEntity(BasketItem basketItem);
}
