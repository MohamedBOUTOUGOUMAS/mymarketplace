package com.example.mymarketplace.hexa.application.rest.responses;

import com.example.mymarketplace.hexa.domain.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientResponse {
    private String id;
    private Client.ClientTypeEnum type;
    private String firstName;
    private String lastName;
    private String reason;
    private String tva;
    private String siret;
    private List<Client.Turnover> turnovers;

    @Data
    @AllArgsConstructor
    public static class Turnover {
        private String year;
        private Integer amount;
    }
}
