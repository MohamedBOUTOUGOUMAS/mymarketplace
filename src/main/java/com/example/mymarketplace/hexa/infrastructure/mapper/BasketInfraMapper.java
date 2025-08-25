package com.example.mymarketplace.hexa.infrastructure.mapper;

import com.example.mymarketplace.hexa.domain.model.Basket;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.model.Client;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.BasketItemEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ClientEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ParticularClientEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ProfessionalClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketInfraMapper {

    BasketInfraMapper INSTANCE = Mappers.getMapper(BasketInfraMapper.class);
    @Mapping(target = "product", source = "product")
    @Mapping(target = "quantity", source = "quantity")
    BasketItemEntity domainToEntity(BasketItem basketItem);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "client", source = "client", qualifiedByName = "bindClients")
    Basket entityToDomain(BasketEntity entity);

    @Named("bindClients")
    default Client bindClients(ClientEntity client) {
        if (client instanceof ProfessionalClientEntity) {
            var mappedClient = new Client();
            mappedClient.setId(client.getId());
            mappedClient.setSiret(((ProfessionalClientEntity) client).getSiret());
            mappedClient.setTva(((ProfessionalClientEntity) client).getTva());
            mappedClient.setReason(((ProfessionalClientEntity) client).getReason());
            mappedClient.setTurnovers(((ProfessionalClientEntity) client).getTurnovers()
                    .stream()
                    .map(t -> new Client.Turnover(t.getYear(), t.getAmount()))
                    .toList()
            );
            mappedClient.setType(Client.ClientTypeEnum.PROFESSIONAL);
            return mappedClient;
        }
        var mappedClient = new Client();
        mappedClient.setId(client.getId());
        mappedClient.setType(Client.ClientTypeEnum.PARTICULAR);
        mappedClient.setFirstName(((ParticularClientEntity) client).getFirstName());
        mappedClient.setLastName(((ParticularClientEntity) client).getLastName());
        return mappedClient;
    }
}
