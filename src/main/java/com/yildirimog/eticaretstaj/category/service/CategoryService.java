package com.yildirimog.eticaretstaj.category.service;

import com.yildirimog.eticaretstaj.category.dto.CategoryDto;
import com.yildirimog.eticaretstaj.category.entity.Category;
import com.yildirimog.eticaretstaj.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public void addCategory(CategoryDto categoryDto){
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        categoryRepository.save(category);
    }

    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> CategoryDto.builder()
                        .name(category.getName())
                        .build())
                .toList();

    }
}
