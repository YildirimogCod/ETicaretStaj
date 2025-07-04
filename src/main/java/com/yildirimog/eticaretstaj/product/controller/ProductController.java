package com.yildirimog.eticaretstaj.product.controller;

import com.yildirimog.eticaretstaj.common.response.ApiResponse;
import com.yildirimog.eticaretstaj.product.dto.ProductDto;
import com.yildirimog.eticaretstaj.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProducts() {
        List<ProductDto> products = productService.getProducts();
        return ResponseEntity.ok(ApiResponse.succes("Products retrieved successfully", products));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable Long id){
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.succes("Product retrieved successfully", product));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestBody @Valid ProductDto productDto ){
        ProductDto created = productService.addProduct(productDto);
        return ResponseEntity
                .status(201) // Created
                .body(ApiResponse.succes("Product added successfully",created));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return ResponseEntity.noContent().build();
    }
}
