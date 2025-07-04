package com.yildirimog.eticaretstaj.category.service;

import com.yildirimog.eticaretstaj.category.dto.CategoryDto;
import com.yildirimog.eticaretstaj.category.entity.Category;
import com.yildirimog.eticaretstaj.category.repository.CategoryRepository;
import com.yildirimog.eticaretstaj.common.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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
    public CategoryDto getCategoryById(Long id){
       Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return CategoryDto.builder()
                    .name(category.get().getName())
                    .build();
        } else {
            throw new ResourceNotFoundException("Category not found");
        }
    }
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
    public void updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            categoryRepository.save(category);
        }
        else {
            throw new ResourceNotFoundException("Category not found");
        }
    }
}
