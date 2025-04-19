package com.ptit.service;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.model.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput);

    Products getProductById(Long id);
}