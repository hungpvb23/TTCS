package com.ptit.repo.impl;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.mapper.ProductRowMapper;
import com.ptit.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput) {
        StringBuilder sql = new StringBuilder("SELECT p.name AS product_name, p.description, pv.price, pv.stock, p.image_url, c.name AS category_name, pv.color " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "JOIN product_variants pv ON p.product_id = pv.product_id " +
                "WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (searchInput.getProductName() != null && !searchInput.getProductName().isEmpty()) {
            sql.append(" AND p.name LIKE ?");
            params.add("%" + searchInput.getProductName() + "%");
        }

        if (searchInput.getCategoryName() != null && !searchInput.getCategoryName().isEmpty()) {
            sql.append(" AND c.name LIKE ?");
            params.add("%" + searchInput.getCategoryName() + "%");
        }

        if (searchInput.getMinPrice() != null) {
            sql.append(" AND pv.price >= ?");
            params.add(searchInput.getMinPrice());
        }

        if (searchInput.getMaxPrice() != null) {
            sql.append(" AND pv.price <= ?");
            params.add(searchInput.getMaxPrice());
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new ProductRowMapper());
    }
}