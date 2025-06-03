package com.clothes.damafashion.controller;

import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  /**
   * Instantiates a new Product controller.
   *
   * @param productService the product service
   */
  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Gets all products.
   *
   * @return the all products
   */
  @GetMapping
  public List<Product> getAllProducts() {
    return productService.findAll();
  }

  /**
   * Gets product by id.
   *
   * @param id the id
   * @return the product by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Create product product.
   *
   * @param product the product
   * @return the product
   */
  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.save(product);
  }

  /**
   * Update product response entity.
   *
   * @param id         the id
   * @param newProduct the new product
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
    return productService.findById(id).map(product -> {
      product.setName(newProduct.getName());
      product.setPrice(newProduct.getPrice());
      product.setDescription(newProduct.getDescription());
      product.setCategory(newProduct.getCategory());
      product.setSupplier(newProduct.getSupplier());
      return ResponseEntity.ok(productService.save(product));
    }).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Delete product response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

    Optional<Product> product = productService.findById(id);

    if (product.isEmpty()) {
      productService.delete(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}