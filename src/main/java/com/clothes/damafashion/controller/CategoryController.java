package com.clothes.damafashion.controller;
import com.clothes.damafashion.entity.Category;
import com.clothes.damafashion.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService categoryService;
  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  // GET: Buscar todas as categorias
  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.findAll();
  }

  // GET: Buscar categoria por ID
  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    return categoryService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // POST: Criar uma nova categoria
  @PostMapping
  public Category createCategory(@RequestBody Category category) {
    return categoryService.save(category);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category newCategory) {
    return categoryService.findById(id).map(category -> {
      category.setName(newCategory.getName());
      return ResponseEntity.ok(categoryService.save(category));
    }).orElse(ResponseEntity.notFound().build());
  }

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