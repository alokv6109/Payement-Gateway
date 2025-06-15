package com.alokcodesback.paymentGateway.exception;

public class ResourceNotFoundExcetion extends RuntimeException {
    String resourceName;
    String fieldName;
    Long fieldValue;

    public ResourceNotFoundExcetion(String entity, String column, long id) {

        super(String.format("%s not found with %s: %s", entity , column, id));
        this.resourceName = entity;
        this.fieldName = column;
        this.fieldValue =  id;

    }
}
