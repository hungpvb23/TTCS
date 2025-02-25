package com.ptit.repo;

import com.ptit.model.Orders;
import com.ptit.model.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantsRepository extends JpaRepository<ProductVariants, Long> {
}
