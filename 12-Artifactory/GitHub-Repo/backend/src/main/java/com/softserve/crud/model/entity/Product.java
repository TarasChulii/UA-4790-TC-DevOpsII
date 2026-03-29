package com.softserve.crud.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter@Setter@NoArgsConstructor
@ToString
@Builder@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, name = "title")
    String name;
    @Column(nullable = false)
    BigDecimal price;
}
