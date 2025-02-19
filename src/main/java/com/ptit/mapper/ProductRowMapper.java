package com.ptit.mapper;

import com.ptit.dto.ProductSearchOutput;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductSearchOutput> {
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
