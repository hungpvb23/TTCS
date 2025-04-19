package com.ptit.repo.impl;

import com.ptit.dto.OrderResponseDTO;
import com.ptit.mapper.OrderRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    public List<OrderResponseDTO> findAllOrdersById(Long id) {
        String sql = "SELECT o.order_id, o.user_id, u.fullname AS customer_name, u.email AS customer_email, " +
                "o.total_price, o.created_at, oi.order_item_id, oi.quantity, oi.price AS item_price, " +
                "pv.variant_id, pv.color, p.product_id, p.name, p.description, p.category_id, o.customer, o.address, pv.price " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.user_id " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN product_variants pv ON oi.variant_id = pv.variant_id " +
                "JOIN products p ON pv.product_id = p.product_id " +
                "WHERE o.order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new OrderRowMapper());
    }
}
