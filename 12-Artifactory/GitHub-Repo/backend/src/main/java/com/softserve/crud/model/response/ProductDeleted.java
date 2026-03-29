package com.softserve.crud.model.response;

public record ProductDeleted(
        String message
) {
    public ProductDeleted() {
        this("Product deleted.");
    }
}
