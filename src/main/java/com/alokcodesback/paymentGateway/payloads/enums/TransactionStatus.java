package com.alokcodesback.paymentGateway.payloads.enums;

import com.alokcodesback.paymentGateway.exception.InstrumentTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionStatus {
    PENDING,
    SUCCESS,
    FAILED;


    @JsonCreator
    public static TransactionStatus fromValue(String value) {
        try {
            return TransactionStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InstrumentTypeException("The value of Transactions " + value + " is not valid"); // Or throw a custom exception
        }
    }
}
