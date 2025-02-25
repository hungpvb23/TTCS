package com.ptit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variants")
@Data
public class ProductVariants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private Integer variantId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}