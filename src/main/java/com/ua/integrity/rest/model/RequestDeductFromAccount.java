package com.ua.integrity.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDeductFromAccount {
    private String cardNumber;
    private String bonusAmount;
    private String deductAmount;
}
