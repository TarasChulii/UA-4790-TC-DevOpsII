package com.softserve.crud.model.response;

import com.softserve.crud.model.entity.Product;

import java.math.BigDecimal;

public record ProductDto(
        String id,
        String name,
        BigDecimal price
) {
    public ProductDto(Product entity) {
        this(
                entity.getId().toString(),
                entity.getName(),
                entity.getPrice()
                );
    }
}
