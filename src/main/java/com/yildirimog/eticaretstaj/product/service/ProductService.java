package com.yildirimog.eticaretstaj.product.service;

import com.yildirimog.eticaretstaj.category.entity.Category;
import com.yildirimog.eticaretstaj.category.repository.CategoryRepository;
import com.yildirimog.eticaretstaj.common.exceptions.ResourceNotFoundException;
import com.yildirimog.eticaretstaj.product.dto.ProductDto;
import com.yildirimog.eticaretstaj.product.entity.Product;
import com.yildirimog.eticaretstaj.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDto> getProducts() {
       List<Product> products = productRepository.findAll();
      return products.stream()
                .map(product -> ProductDto.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stockQuantity(product.getStockQuantity())
                        .build())
                .toList();
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    public void addProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("Category not found");
        }
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stockQuantity(productDto.getStockQuantity())
                .category(category.orElseThrow(() -> new RuntimeException("Category not found")))
                .build();
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
    public void updateProduct(Long id,ProductDto productDto){
       Optional<Product> product = productRepository.findById(id);
       if (product.isPresent()){
           Product product1 = product.get();
              product1.setName(productDto.getName());
              product1.setDescription(productDto.getDescription());
              product1.setPrice(productDto.getPrice());
              product1.setStockQuantity(productDto.getStockQuantity());
              Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
              product1.setCategory(category.orElseThrow(() -> new RuntimeException("Category not found")));
              productRepository.save(product1);
         } else {
              throw new ResourceNotFoundException("Product not found");
            }
       Product updatedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productDto.setName(updatedProduct.getName());
        productDto.setDescription(updatedProduct.getDescription());
        productDto.setPrice(updatedProduct.getPrice());
        productDto.setStockQuantity(updatedProduct.getStockQuantity());
        productDto.setCategoryId(updatedProduct.getCategory().getId());

    }
}
