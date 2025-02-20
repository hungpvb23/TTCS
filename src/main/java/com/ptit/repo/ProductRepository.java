package com.ptit.repo;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;

import java.util.List;

public interface ProductRepository {
    List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput);
}
