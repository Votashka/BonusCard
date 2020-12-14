package com.ua.integrity.rest.model;

import lombok.Data;

@Data
public class RequestBonusType {
    private String bonusType;

    public RequestBonusType(String bonusType) {
        this.bonusType = bonusType;
    }
}
