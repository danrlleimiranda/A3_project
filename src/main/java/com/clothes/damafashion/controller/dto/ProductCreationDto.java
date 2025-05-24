package com.clothes.damafashion.controller.dto;

import com.clothes.damafashion.entity.Category;
import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.entity.Supplier;

public record ProductCreationDto(String name, Double price, String description,
                                 Category category, Supplier supplier) {

    public Product toEntity() {
        return new Product(name, price, description, category, supplier);
    }
}
