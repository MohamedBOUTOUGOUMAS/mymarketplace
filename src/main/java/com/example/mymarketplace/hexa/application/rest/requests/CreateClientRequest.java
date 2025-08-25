package com.example.mymarketplace.hexa.application.rest.requests;

import com.example.mymarketplace.hexa.domain.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class CreateClientRequest {
    @NotBlank
    private Client.ClientTypeEnum type;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String reason;
    @NotBlank
    private String tva;
    @NotBlank
    private String siret;
    @NotEmpty
    private List<Client.Turnover> turnovers;

    @Data
    @AllArgsConstructor
    public static class Turnover {
        @NotBlank
        private String year;
        @NotBlank
        private Integer total;
    }
}
