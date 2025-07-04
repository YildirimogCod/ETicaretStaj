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

    public ProductDto addProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.categoryId());
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("Category not found");
        }
        Product product = Product.builder()
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .stockQuantity(productDto.stockQuantity())
                .categories(List.of(category.get()))
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
       if (product.isPresent()) {
           Product product1 = product.get();
              product1.setName(productDto.name());
              product1.setDescription(productDto.description());
              product1.setPrice(productDto.price());
              product1.setStockQuantity(productDto.stockQuantity());
              Optional<Category> category = categoryRepository.findById(productDto.categoryId());
            if (category.isPresent()) {
                product1.setCategories(List.of(category.get()));
              productRepository.save(product1);
         } else {
              throw new ResourceNotFoundException("Product not found");
            }
       Product updatedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
              productDto = ProductDto.builder()
                     .name(updatedProduct.getName())
                     .description(updatedProduct.getDescription())
                     .price(updatedProduct.getPrice())
                     .stockQuantity(updatedProduct.getStockQuantity())
                     .build();
         } else {
           throw new ResourceNotFoundException("Product not found");
       }


    }
}
