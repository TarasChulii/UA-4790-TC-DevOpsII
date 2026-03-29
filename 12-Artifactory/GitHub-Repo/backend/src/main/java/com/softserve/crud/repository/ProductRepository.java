package com.softserve.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserve.crud.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}