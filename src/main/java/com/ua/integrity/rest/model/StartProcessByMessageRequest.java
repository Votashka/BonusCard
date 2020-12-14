package com.ua.integrity.rest.model;

import lombok.Data;

@Data
public class StartProcessByMessageRequest {
    private String cardNumber;
    private Double amount;
}
