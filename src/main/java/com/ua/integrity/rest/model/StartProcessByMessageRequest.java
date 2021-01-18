package com.ua.integrity.rest.model;

import lombok.Data;

@Data
public class StartProcessByMessageRequest {
    private String cardNumber;
    private Double amount; // TODO: 18.01.2021 first mistake is to use double - double in Java cannot correctly describe number with float point, better to use string for representing and bigdecimal for calculating
}
