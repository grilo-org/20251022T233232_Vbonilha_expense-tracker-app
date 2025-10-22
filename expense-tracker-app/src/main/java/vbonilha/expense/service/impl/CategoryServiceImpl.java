package vbonilha.expense.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vbonilha.expense.dto.CategoryDto;
import vbonilha.expense.entity.Category;
import vbonilha.expense.exceptions.ResourceNotFoundException;
import vbonilha.expense.mapper.CategoryMapper;
import vbonilha.expense.repository.CategoryRepository;
import vbonilha.expense.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // convert CategoryDto to Category entity
        Category category = CategoryMapper.mapToCategory(categoryDto);

        // save category object into database table - categories
        Category savedCategory = categoryRepository.save(category);


        // convert savedCategory to CategoryDto
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map((category) -> CategoryMapper.mapToCategoryDto(category))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {

        // get category entity from the database by category id
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        category.setName(categoryDto.name());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        // get category entity from the database by category id
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        categoryRepository.delete(category);

    }
}
