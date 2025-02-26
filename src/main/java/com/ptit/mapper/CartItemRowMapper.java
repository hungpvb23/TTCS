package com.ptit.mapper;

import com.ptit.dto.CartItemResponseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemRowMapper implements RowMapper<CartItemResponseDTO> {

    @Override
    public CartItemResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartItemResponseDTO dto = new CartItemResponseDTO();
        dto.setCartItemId(rs.getLong("cart_item_id"));
        dto.setUserId(rs.getLong("user_id"));
        dto.setQuantity(rs.getLong("quantity"));
        dto.setPrice(rs.getBigDecimal("price"));
        dto.setVariantId(rs.getLong("variant_id"));
        dto.setColor(rs.getString("color"));
        dto.setProductId(rs.getLong("product_id"));
        dto.setProductName(rs.getString("name"));
        dto.setDescription(rs.getString("description"));
        dto.setCategoryId(rs.getLong("category_id"));
        return dto;
    }
}