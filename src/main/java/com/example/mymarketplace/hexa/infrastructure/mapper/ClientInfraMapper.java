package com.example.mymarketplace.hexa.infrastructure.mapper;

import com.example.mymarketplace.hexa.domain.model.Client;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ParticularClientEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ProfessionalClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientInfraMapper {
    ClientInfraMapper INSTANCE = Mappers.getMapper(ClientInfraMapper.class);

    ProfessionalClientEntity domainToProfessionalEntity(Client client);
    ParticularClientEntity domainToParticularEntity(Client client);

    Client professionalEntityToDomain(ProfessionalClientEntity client);
    Client particularEntityToDomain(ParticularClientEntity client);
}
