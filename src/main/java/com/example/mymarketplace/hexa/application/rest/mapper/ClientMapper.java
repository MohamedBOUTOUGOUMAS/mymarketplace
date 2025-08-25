package com.example.mymarketplace.hexa.application.rest.mapper;

import com.example.mymarketplace.hexa.application.rest.requests.CreateClientRequest;
import com.example.mymarketplace.hexa.application.rest.responses.CreateClientResponse;
import com.example.mymarketplace.hexa.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client requestToDomain(CreateClientRequest request);
    CreateClientResponse domainToResponse(Client client);
}
