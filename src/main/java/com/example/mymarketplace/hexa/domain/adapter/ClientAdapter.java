package com.example.mymarketplace.hexa.domain.adapter;

import com.example.mymarketplace.hexa.domain.exception.ClientNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Client;
import com.example.mymarketplace.hexa.domain.port.primary.ClientPort;
import com.example.mymarketplace.hexa.domain.port.secondary.persistence.ClientPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ClientAdapter implements ClientPort {

    @Autowired
    private ClientPersistencePort clientPersistencePort;

    @Override
    public Client findClientById(String id) throws ClientNotFoundException {
        return clientPersistencePort.findClientById(id);
    }

    @Override
    public Client create(Client client) {
        return clientPersistencePort.create(client);
    }
}
