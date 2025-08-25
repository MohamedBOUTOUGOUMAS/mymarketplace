package com.example.mymarketplace.hexa.domain.port.secondary.persistence;

import com.example.mymarketplace.hexa.domain.exception.ClientNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Client;

public interface ClientPersistencePort {
    Client findClientById(String id) throws ClientNotFoundException;

    Client create(Client client);
}
