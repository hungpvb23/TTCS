package com.ptit.repo.impl;

import com.ptit.dto.CartItemResponseDTO;
import com.ptit.mapper.CartItemRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    public List<CartItemResponseDTO> getCartItems(Long userId) {
        String sql = "SELECT ci.id as cart_item_id , ci.user_id, ci.quantity, pv.price, pv.variant_id, pv.color, p.product_id, p.name, p.description, p.category_id " +
                "FROM cart_items ci " +
                "JOIN product_variants pv ON ci.variant_id = pv.variant_id " +
                "JOIN products p ON pv.product_id = p.product_id " +
                "WHERE ci.user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new CartItemRowMapper());
    }
}
