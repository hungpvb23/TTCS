package com.ptit.mapper;

import com.ptit.dto.OrderResponseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderResponseDTO> {

    @Override
    public OrderResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(rs.getLong("order_id"));
        dto.setUserId(rs.getLong("user_id"));
        dto.setCustomerName(rs.getString("customer_name"));
        dto.setCustomerEmail(rs.getString("customer_email"));
        dto.setTotalPrice(rs.getBigDecimal("total_price"));
        dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        dto.setOrderItemId(rs.getLong("order_item_id"));
        dto.setQuantity(rs.getLong("quantity"));
        return dto;
    }
}
