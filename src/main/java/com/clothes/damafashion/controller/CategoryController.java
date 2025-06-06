package com.clothes.damafashion.controller;
import com.clothes.damafashion.controller.dto.CategoryCreationDto;
import com.clothes.damafashion.entity.Category;
import com.clothes.damafashion.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Category controller.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  /**
   * Instantiates a new Category controller.
   *
   * @param categoryService the category service
   */
  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * Gets all categories.
   *
   * @return the all categories
   */
  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.findAll();
  }

  /**
   * Gets category by id.
   *
   * @param id the id
   * @return the category by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    return categoryService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Create category.
   *
   * @param category the category
   * @return the category
   */
  @PostMapping
  public Category createCategory(@RequestBody CategoryCreationDto category) {
    return categoryService.save(category.toEntity());
  }

  /**
   * Update category response entity.
   *
   * @param id          the id
   * @param newCategory the new category
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryCreationDto newCategory) {
    return categoryService.findById(id).map(category -> {
      category.setName(newCategory.name());
      return ResponseEntity.ok(categoryService.save(category));
    }).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Delete category response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    Optional<Category> category = categoryService.findById(id);

    if (category.isEmpty()) {
      categoryService.delete(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}