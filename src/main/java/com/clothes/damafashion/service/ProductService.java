package com.clothes.damafashion.service;

import com.clothes.damafashion.entity.Product;
import com.clothes.damafashion.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Listar todos os produtos
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Buscar um produto por ID
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Salvar um novo produto
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Atualizar um produto existente
    public Optional<Product> update(Long id, Product newProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            product.setDescription(newProduct.getDescription());
            product.setCategory(newProduct.getCategory());
            product.setSupplier(newProduct.getSupplier());
            return productRepository.save(product);
        });
    }

    // Deletar produto por ID
    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}