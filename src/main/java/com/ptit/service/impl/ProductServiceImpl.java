package com.ptit.service.impl;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.mapper.ProductRowMapper;
import com.ptit.repo.ProductRepository;
import com.ptit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput) {
        return productRepository.searchProducts(searchInput);
    }
}
