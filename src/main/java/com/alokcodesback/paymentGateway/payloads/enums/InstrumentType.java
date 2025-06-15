package com.alokcodesback.paymentGateway.payloads.enums;

import com.alokcodesback.paymentGateway.exception.InstrumentTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum InstrumentType {
    ACCOUNT,
    CARD;

    @JsonCreator
    public static InstrumentType fromValue(String value) {
        try {
            return InstrumentType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InstrumentTypeException("The value of InstrumentType " + value + " is not valid"); // Or throw a custom exception
        }
    }
}
