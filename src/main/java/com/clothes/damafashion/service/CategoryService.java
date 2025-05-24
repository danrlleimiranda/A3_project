package com.clothes.damafashion.service;

import com.clothes.damafashion.entity.Category;
import com.clothes.damafashion.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> update(Long id, Category newCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(newCategory.getName());
            return categoryRepository.save(category);
        });
    }

    public boolean delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}