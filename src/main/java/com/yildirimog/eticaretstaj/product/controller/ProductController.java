package com.yildirimog.eticaretstaj.product.controller;

import com.yildirimog.eticaretstaj.product.dto.ProductDto;
import com.yildirimog.eticaretstaj.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(Long id){
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @PostMapping
    public ResponseEntity<Void> addProduct(ProductDto productDto){
        productService.addProduct(productDto);
        return ResponseEntity
                .status(201) // Created
                .build();
    }
}
