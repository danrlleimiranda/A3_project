package com.clothes.damafashion.controller.dto;

import com.clothes.damafashion.entity.Category;
import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.entity.Supplier;

/**
 * The type Product creation dto.
 */
public record ProductCreationDto(String name, Double price, String description,
                                 Category category, Supplier supplier) {

    /**
     * To entity product.
     *
     * @return the product
     */
    public Product toEntity() {
        return new Product(name, price, description, category, supplier);
    }
}
