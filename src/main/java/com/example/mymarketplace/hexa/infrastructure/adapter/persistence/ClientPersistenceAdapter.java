package com.example.mymarketplace.hexa.infrastructure.adapter.persistence;

import com.example.mymarketplace.hexa.domain.exception.ClientNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Client;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.ClientPersistencePort;
import com.example.mymarketplace.hexa.infrastructure.mapper.ClientInfraMapper;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ParticularClientEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ProfessionalClientEntity;
import com.example.mymarketplace.hexa.infrastructure.persistence.repositories.ClientJpaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class ClientPersistenceAdapter implements ClientPersistencePort {
    @Autowired
    private ClientJpaRepository clientJpaRepository;
    @Override
    public Client findClientById(String id) throws ClientNotFoundException {
        return clientJpaRepository.findById(id)
                .map(c -> c instanceof ProfessionalClientEntity ?
                        ClientInfraMapper.INSTANCE.professionalEntityToDomain((ProfessionalClientEntity) c) :
                        ClientInfraMapper.INSTANCE.particularEntityToDomain((ParticularClientEntity) c))
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    @Transactional
    public Client create(Client client) {
        var entity = client.getType() == Client.ClientTypeEnum.PROFESSIONAL ?
                ClientInfraMapper.INSTANCE.domainToProfessionalEntity(client) :
                ClientInfraMapper.INSTANCE.domainToParticularEntity(client);
        var createdClient = clientJpaRepository.save(entity);
        return createdClient instanceof ProfessionalClientEntity ?
                ClientInfraMapper.INSTANCE.professionalEntityToDomain((ProfessionalClientEntity) createdClient) :
                ClientInfraMapper.INSTANCE.particularEntityToDomain((ParticularClientEntity) createdClient);
    }
}
