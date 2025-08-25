package com.example.mymarketplace.hexa.application.rest.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalToPayResponse {
    private BigInteger total;
}
