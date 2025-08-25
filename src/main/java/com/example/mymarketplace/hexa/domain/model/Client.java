package com.example.mymarketplace.hexa.domain.model;

import com.example.mymarketplace.hexa.domain.exception.ClientYearTurnoverNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;
import java.util.List;

@Data
public class Client {
    public enum ClientTypeEnum { PROFESSIONAL, PARTICULAR };

    private String id;
    private ClientTypeEnum type;
    private String firstName;
    private String LastName;
    private String reason;
    private String tva;
    private String siret;
    private List<Turnover> turnovers;
    private Integer enableDiscountMaxAmount;

    @Data
    @AllArgsConstructor
    public static class Turnover {
        private String year;
        private Integer amount;
    }

    public boolean needApplyDiscount() throws ClientYearTurnoverNotFoundException {
        assert enableDiscountMaxAmount != null;
        int year = Year.now().getValue() - 1;
        return getTurnoverAmountForYear(year) > enableDiscountMaxAmount;
    }

    private int getTurnoverAmountForYear(int year) throws ClientYearTurnoverNotFoundException {
        for(Client.Turnover t : turnovers) {
            if (Integer.parseInt(t.getYear()) == year) {
                return t.getAmount();
            }
        }
        throw new ClientYearTurnoverNotFoundException();
    }
}
