package com.example.mymarketplace.hexa.domain.model;

import com.example.mymarketplace.hexa.domain.exception.ClientYearTurnoverNotFoundException;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class Basket {
    private String Id;
    private Client client;
    private List<BasketItem> basketItems;

    public BigInteger calculateTotal() throws ClientYearTurnoverNotFoundException {
        return switch (client.getType()) {
            case PARTICULAR -> calculateForParticular();
            case PROFESSIONAL -> calculateForProfessional();
            default -> throw new IllegalArgumentException("We cannot calculate the total");
        };
    }

    private BigInteger calculateForProfessional() throws ClientYearTurnoverNotFoundException {
        BigInteger total = new BigInteger("0");
        for (BasketItem item : basketItems) {
            total = total.add(calculateTotalByItem(item));
        }
        return total;
    }

    private BigInteger calculateTotalByItem(BasketItem item) throws ClientYearTurnoverNotFoundException {
        var total = (client.needApplyDiscount() ?
                item.getProduct().getPriceForProfessionalAfterDiscount() :
                item.getProduct().getPriceForProfessional())
                * item.getQuantity();
        return new BigInteger(String.valueOf(total));
    }

    private BigInteger calculateForParticular() {
        var total = basketItems.stream()
                .map(item -> item.getProduct().getPriceForParticular() * item.getQuantity())
                .reduce(Integer::sum)
                .orElse(0);
        return new BigInteger(String.valueOf(total));
    }
}
