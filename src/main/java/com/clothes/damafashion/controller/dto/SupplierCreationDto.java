package com.clothes.damafashion.controller.dto;


import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.entity.Supplier;

import java.util.List;

/**
 * The type Supplier creation dto.
 */
public record SupplierCreationDto(String name, String contact, List<Product> products) {


  /**
   * To entity supplier.
   *
   * @return the supplier
   */
  public Supplier toEntity() {
    return new Supplier(name, contact, products);
  }
}
