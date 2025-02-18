package com.ptit.service;

import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public List<ProductSearchOutput> searchProducts(ProductSearchInput searchInput) {
//        String sql = "SELECT p.name AS product_name, p.description, p.price, p.stock, p.image_url, c.name AS category_name, c.parent_id " +
//                "FROM Products p JOIN Categories c ON p.category_id = c.id " +
//                "WHERE c.name LIKE ?";
//        return jdbcTemplate.query(sql, new Object[]{"%" + searchInput.getCategoryName() + "%"}, new ProductRowMapper());
        return null;
    }

    private static class ProductRowMapper implements RowMapper<ProductSearchOutput> {
        @Override
        public ProductSearchOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductSearchOutput product = new ProductSearchOutput();
            product.setProductName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setStock(rs.getInt("stock"));
            product.setImageUrl(rs.getString("image_url"));
            product.setCategoryName(rs.getString("category_name"));
            product.setParentId(rs.getInt("parent_id"));
            return product;
        }
    }
}
