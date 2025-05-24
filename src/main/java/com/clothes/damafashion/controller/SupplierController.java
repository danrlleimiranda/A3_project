package com.clothes.damafashion.controller;

import com.clothes.damafashion.entity.Supplier;
import com.clothes.damafashion.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // GET: Buscar todos os fornecedores
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.findAll();
    }

    // GET: Buscar fornecedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar um novo fornecedor
    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    // PUT: Atualizar um fornecedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier newSupplier) {
        return supplierService.update(id, newSupplier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Excluir um fornecedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        if (supplierService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}