package com.clothes.damafashion.service.exception;

/**
 * The type Crop not found exception.
 */
public class ProductNotFoundException extends NotFoundException {


  /**
   * Instantiates a new Crop not found exception.
   */
  public ProductNotFoundException() {
    super("Produto não encontrado!");
  }
}
