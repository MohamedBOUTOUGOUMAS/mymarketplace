package com.example.mymarketplace.hexa.domain.port.primary;

import com.example.mymarketplace.hexa.domain.exception.ClientNotFoundException;
import com.example.mymarketplace.hexa.domain.model.Client;

public interface ClientPort {
    Client findClientById(String id) throws ClientNotFoundException;

    Client create(Client client);
}
