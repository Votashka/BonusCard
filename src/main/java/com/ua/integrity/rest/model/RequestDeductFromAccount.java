package com.ua.integrity.rest.model;

import lombok.Data;

@Data
public class RequestDeductFromAccount {
    private String cardNumber;
    private String bonusAmount;
    private String deductAmount;

    public RequestDeductFromAccount(String cardNumber, String bonusAmount, String deductAmount) {
        this.cardNumber = cardNumber;
        this.bonusAmount = bonusAmount;
        this.deductAmount = deductAmount;
    }
}
