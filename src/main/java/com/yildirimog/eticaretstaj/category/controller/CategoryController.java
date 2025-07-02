package com.yildirimog.eticaretstaj.category.controller;

import com.yildirimog.eticaretstaj.category.dto.CategoryDto;
import com.yildirimog.eticaretstaj.category.entity.Category;
import com.yildirimog.eticaretstaj.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody  CategoryDto categoryDto){
        //categoryService.addCategory(categoryDto);
        return ResponseEntity
                .status(201) // Created
                .build();
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
}
