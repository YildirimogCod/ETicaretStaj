package com.yildirimog.eticaretstaj.category.controller;
import com.yildirimog.eticaretstaj.category.dto.CategoryDto;
import com.yildirimog.eticaretstaj.category.service.CategoryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Void> addCategory(@RequestBody @Valid CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return ResponseEntity
                .status(201) // Created
                .build();
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if (categoryDto != null) {
            return ResponseEntity.ok(categoryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.noContent().build();
    }
}
