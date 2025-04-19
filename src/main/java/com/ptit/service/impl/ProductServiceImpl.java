package com.ptit.service.impl;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.mapper.ProductRowMapper;
import com.ptit.model.Products;
import com.ptit.repo.ProductRepo;
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

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput) {
        return productRepository.searchProducts(searchInput);
    }

    @Override
    public Products getProductById(Long id) {
        return productRepo.findById(id).get();
    }
}
