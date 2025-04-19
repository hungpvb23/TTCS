package com.ptit.repo;

import com.ptit.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products, Long> {
}
