package com.example.mymarketplace.hexa.application.rest.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBasketResponse {
    private String Id;
    private Client client;
    private List<BasketItem> basketItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Client {
        private String id;
        private com.example.mymarketplace.hexa.domain.model.Client.ClientTypeEnum type;
        private String firstName;
        private String lastName;
        private String reason;
        private String tva;
        private String siret;
        private List<Turnover> turnovers;

        @Data
        @AllArgsConstructor
        public static class Turnover {
            private String year;
            private Integer amount;
        }
    }

    public record BasketItem (String productId, String productName, String productCategory, Integer quantity){}
}
