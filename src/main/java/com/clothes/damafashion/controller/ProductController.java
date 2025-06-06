package com.clothes.damafashion.controller;

import com.clothes.damafashion.controller.dto.ProductCreationDto;
import com.clothes.damafashion.entity.Category;
import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.entity.Supplier;
import com.clothes.damafashion.service.CategoryService;
import com.clothes.damafashion.service.ProductService;
import com.clothes.damafashion.service.SupplierService;
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
  private final CategoryService categoryService;
  private final SupplierService supplierService;

  /**
   * Instantiates a new Product controller.
   *
   * @param productService  the product service
   * @param categoryService the category service
   * @param supplierService the supplier service
   */
  @Autowired
  public ProductController(ProductService productService, CategoryService categoryService, SupplierService supplierService) {
    this.productService = productService;
    this.categoryService = categoryService;
    this.supplierService = supplierService;
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
  public ResponseEntity<Product> createProduct(@RequestBody ProductCreationDto productCreationDto) {
    Product product =  productService.save(productCreationDto.toEntity(categoryService, supplierService));
    return ResponseEntity.ok(product);
  }

  /**
   * Update product response entity.
   *
   * @param id         the id
   * @param newProduct the new product
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductCreationDto newProduct) {
    Optional<Category> categoryOptional = categoryService.findById(newProduct.categoryId());
    Optional<Supplier> supplierOptional = supplierService.findById(newProduct.supplierId());
    if (categoryOptional.isEmpty() || supplierOptional.isEmpty()) {
      throw new IllegalArgumentException("Category or Supplier not found");
    }

    return productService.findById(id).map(product -> {
      product.setName(newProduct.name());
      product.setPrice(newProduct.price());
      product.setDescription(newProduct.description());
      product.setCategory(categoryOptional.get());
      product.setSupplier(supplierOptional.get());
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