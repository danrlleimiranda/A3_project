package com.clothes.damafashion.controller;

import com.clothes.damafashion.controller.dto.SupplierCreationDto;
import com.clothes.damafashion.entity.Supplier;
import com.clothes.damafashion.service.ProductService;
import com.clothes.damafashion.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Supplier controller.
 */
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final ProductService productService;

    /**
     * Instantiates a new Supplier controller.
     *
     * @param supplierService the supplier service
     * @param productService  the product service
     */
    @Autowired
    public SupplierController(SupplierService supplierService, ProductService productService) {
        this.supplierService = supplierService;
        this.productService = productService;
    }

    /**
     * Gets all suppliers.
     *
     * @return the all suppliers
     */
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.findAll();
    }

    /**
     * Gets supplier by id.
     *
     * @param id the id
     * @return the supplier by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create supplier supplier.
     *
     * @param supplier the supplier
     * @return the supplier
     */
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        supplierService.save(supplier);
        return ResponseEntity.ok().build();
    }

    /**
     * Update supplier response entity.
     *
     * @param id          the id
     * @param newSupplier the new supplier
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody SupplierCreationDto newSupplier) {
        return supplierService.update(id, newSupplier.toEntity(productService))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete supplier response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        if (supplierService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}