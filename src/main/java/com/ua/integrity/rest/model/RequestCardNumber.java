package com.ua.integrity.rest.model;

import lombok.Data;

@Data
public class RequestCardNumber {
    private String cardNumber;

    public RequestCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}