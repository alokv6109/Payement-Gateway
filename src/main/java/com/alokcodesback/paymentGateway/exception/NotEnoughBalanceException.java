package com.alokcodesback.paymentGateway.exception;

import org.aspectj.weaver.ast.Not;

public class NotEnoughBalanceException extends RuntimeException{
    public NotEnoughBalanceException(String message){
        super(message);
    }
}
