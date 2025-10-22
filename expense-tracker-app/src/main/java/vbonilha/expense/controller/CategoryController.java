package vbonilha.expense.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vbonilha.expense.dto.CategoryDto;
import vbonilha.expense.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories") // Define the base URL
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    // Build create category REST API
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // Build get category by id REST API
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
        CategoryDto category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    // Build get all categories REST API
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Build update category REST API
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,
                                                      @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    // Build delete category REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}
