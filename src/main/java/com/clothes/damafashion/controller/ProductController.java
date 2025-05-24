package com.clothes.damafashion.controller;

import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  // GET: Buscar todos os produtos
  @GetMapping
  public List<Product> getAllProducts() {
    return productService.findAll();
  }

  // GET: Buscar produto por ID
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // POST: Criar um novo produto
  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.save(product);
  }

  // PUT: Atualizar um produto existente
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

  // DELETE: Excluir um produto
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