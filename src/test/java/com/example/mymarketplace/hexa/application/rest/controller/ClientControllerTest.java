package com.example.mymarketplace.hexa.application.rest.controller;

import com.example.mymarketplace.hexa.application.rest.requests.CreateClientRequest;
import com.example.mymarketplace.hexa.domain.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_be_able_to_create_particular_client() throws Exception {
        var clientToCreate = new CreateClientRequest();
        clientToCreate.setFirstName("Jhon");
        clientToCreate.setLastName("doe");
        clientToCreate.setType(Client.ClientTypeEnum.PARTICULAR);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .content(asJsonString(clientToCreate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void should_be_able_to_create_professional_client() throws Exception {
        var clientToCreate = new CreateClientRequest();
        clientToCreate.setTva("20");
        clientToCreate.setSiret("876534568765");
        clientToCreate.setReason("Prestataire de service");
        clientToCreate.setTurnovers(List.of(new Client.Turnover("2024", 1000000000)));
        clientToCreate.setType(Client.ClientTypeEnum.PROFESSIONAL);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .content(asJsonString(clientToCreate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.siret").value("876534568765"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.turnovers[0].year").value("2024"));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}