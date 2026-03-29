package com.softserve.crud.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Not Found");
    }
}
