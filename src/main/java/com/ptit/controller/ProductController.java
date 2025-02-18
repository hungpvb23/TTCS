package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/search")
    public ResponseEntity<BaseResponse> searchProducts(@RequestBody ProductSearchInput searchInput) {
        List<ProductSearchOutput> products = productService.searchProducts(searchInput);
        BaseResponse response = BaseResponse.builder()
                .code(200)
                .data(products)
                .build();
        return ResponseEntity.ok(response);
    }
}
