package com.clothes.damafashion.controller;

import com.clothes.damafashion.entity.Supplier;
import com.clothes.damafashion.service.SupplierService;
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

    /**
     * Instantiates a new Supplier controller.
     *
     * @param supplierService the supplier service
     */
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
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
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    /**
     * Update supplier response entity.
     *
     * @param id          the id
     * @param newSupplier the new supplier
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier newSupplier) {
        return supplierService.update(id, newSupplier)
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