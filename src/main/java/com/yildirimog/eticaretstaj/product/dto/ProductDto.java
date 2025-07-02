package com.yildirimog.eticaretstaj.product.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    @DecimalMin(value = "0.0", message = "Price must be a positive number")
    private BigDecimal price;
    @Min(value = 0, message = "Stock quantity must be zero or greater")
    private int stockQuantity;
    @NotBlank(message = "Category ID cannot be blank")
    private Long categoryId;
}
