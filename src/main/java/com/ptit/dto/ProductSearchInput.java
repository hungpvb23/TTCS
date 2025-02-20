package com.ptit.dto;

import lombok.Data;

@Data
public class ProductSearchInput {
    private String productName;
    private String categoryName;
    private Double minPrice;
    private Double maxPrice;
}