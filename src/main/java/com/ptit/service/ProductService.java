package com.ptit.service;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput) {
        String sql = "SELECT p.name AS product_name, p.description, p.price, p.stock, p.image_url, c.name AS category_name, c.parent_id " +
                "FROM Products p JOIN Categories c ON p.category_id = c.category_id " +
                "WHERE c.name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + searchInput.getCategoryName() + "%"}, new ProductRowMapper());
    }

}
