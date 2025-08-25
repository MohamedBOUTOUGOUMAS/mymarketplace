package com.example.mymarketplace.hexa.application.rest.controller;

import com.example.mymarketplace.hexa.application.rest.mapper.ClientMapper;
import com.example.mymarketplace.hexa.application.rest.requests.CreateClientRequest;
import com.example.mymarketplace.hexa.application.rest.responses.CreateClientResponse;
import com.example.mymarketplace.hexa.domain.port.primary.ClientPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@NoArgsConstructor
public class ClientController {

    @Autowired
    private ClientPort clientPort;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateClientResponse createClient(@Valid @RequestBody CreateClientRequest request) {
        return ClientMapper.INSTANCE.domainToResponse(clientPort.create(ClientMapper.INSTANCE.requestToDomain(request)));
    }
}
