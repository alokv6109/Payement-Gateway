package com.alokcodesback.paymentGateway.payloads.enums;

import com.alokcodesback.paymentGateway.exception.InstrumentTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum CardType {
    DEBIT,
    CREDIT;

    @JsonCreator
    public static CardType fromValue(String value) {
        try {
            return CardType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InstrumentTypeException("The value of CardType " + value + " is not valid"); // Or throw a custom exception
        }
    }


}
