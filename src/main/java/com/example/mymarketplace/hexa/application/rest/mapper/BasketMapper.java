package com.example.mymarketplace.hexa.application.rest.mapper;

import com.example.mymarketplace.hexa.application.rest.requests.BasketItemRequest;
import com.example.mymarketplace.hexa.application.rest.responses.UpsertBasketResponse;
import com.example.mymarketplace.hexa.domain.model.Basket;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketMapper {

    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "product.name", source = "productName")
    @Mapping(target = "product.category", source = "productCategory")
    @Mapping(target = "quantity", source = "quantity")
    BasketItem requestToDomain(BasketItemRequest request);

    @Mapping(target = "basketItems", source = "basketItems", qualifiedByName = "bindBasketItem")
    UpsertBasketResponse domainToResponse(Basket basket);

    @Named("bindBasketItem")
    default UpsertBasketResponse.BasketItem bindBasketItem(BasketItem basketItem) {
        return new UpsertBasketResponse.BasketItem(basketItem.getProduct().getId(), basketItem.getProduct().getName(), basketItem.getProduct().getCategory().toString(), basketItem.getQuantity());
    }
}
