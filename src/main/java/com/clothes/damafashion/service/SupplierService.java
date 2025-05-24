package com.clothes.damafashion.service;

import com.clothes.damafashion.entity.Supplier;
import com.clothes.damafashion.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Listar todos os fornecedores
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    // Buscar um fornecedor pelo ID
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    // Criar um novo fornecedor
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Atualizar um fornecedor existente
    public Optional<Supplier> update(Long id, Supplier newSupplier) {
        return supplierRepository.findById(id).map(supplier -> {
            supplier.setName(newSupplier.getName());
            supplier.setContact(newSupplier.getContact());
            return supplierRepository.save(supplier);
        });
    }

    // Deletar fornecedor pelo ID
    public boolean delete(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }
}