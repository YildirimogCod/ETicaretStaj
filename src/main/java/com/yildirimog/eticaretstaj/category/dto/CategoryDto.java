package com.yildirimog.eticaretstaj.category.dto;

import com.yildirimog.eticaretstaj.product.dto.ProductDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public record CategoryDto(
    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    String name
) {
}
//    @NotBlank(message = "Category name cannot be blank")
//    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")


