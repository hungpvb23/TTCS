package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.dto.ProductSearchInput;
import com.ptit.dto.ProductSearchOutput;
import com.ptit.model.Products;
import com.ptit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

     @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable Long id) {
         Products product = productService.getProductById(id);
         BaseResponse response = BaseResponse.builder()
                 .code(200)
                 .data(product)
                 .build();
         return ResponseEntity.ok(response);
     }
}
