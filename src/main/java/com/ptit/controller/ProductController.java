package com.ptit.controller;

import com.ptit.model.Products;
import com.ptit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public List<Products> searchProductsByCategory(@RequestParam String categoryName) {
//        return productService.findProductsByCategory(categoryName);
        return null;
    }
}
