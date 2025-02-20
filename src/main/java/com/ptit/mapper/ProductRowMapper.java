package com.ptit.mapper;

import com.ptit.dto.ProductSearchOutput;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class ProductRowMapper implements RowMapper<ProductSearchOutput> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0");

    @Override
    public ProductSearchOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductSearchOutput product = new ProductSearchOutput();
        product.setProductName(rs.getString("product_name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(decimalFormat.format(rs.getDouble("price")));
        product.setStock(rs.getInt("stock"));
        product.setImageUrl(rs.getString("image_url"));
        product.setCategoryName(rs.getString("category_name"));
        product.setColor(rs.getString("color"));
        return product;
    }
}
