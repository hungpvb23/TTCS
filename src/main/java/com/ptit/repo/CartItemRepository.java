package com.ptit.repo;

import com.ptit.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findByUserIdAndVariantId(Long userId, Long variantId);

    @Query(value = "SELECT * FROM cart_items WHERE user_id = :userId AND id IN (:cartItemIds)", nativeQuery = true)
    List<CartItem> findSelectedCartItems(@Param("userId") Long userId, @Param("cartItemIds") List<Long> cartItemIds);

    void deleteByIdIn(List<Long> cartItemIds);
}
