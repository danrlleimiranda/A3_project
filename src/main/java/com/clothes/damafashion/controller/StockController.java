package com.clothes.damafashion.controller;

import com.clothes.damafashion.entity.Stock;
import com.clothes.damafashion.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // GET: Buscar todos os estoques
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.findAll();
    }

    // GET: Buscar o estoque de um produto específico pelo ID
    @GetMapping("/{productId}")
    public ResponseEntity<Stock> getStockByProductId(@PathVariable Long productId) {
        return stockService.findByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Atualizar o estoque de um produto
    @PutMapping("/{productId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long productId, @RequestBody Stock updatedStock) {
        return stockService.updateQuantity(productId, updatedStock.getQuantity())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar/Atualizar um estoque
    @PostMapping
    public Stock createOrUpdateStock(@RequestBody Stock stock) {
        return stockService.saveOrUpdate(stock);
    }

    // DELETE: Excluir o estoque de um produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        if (stockService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}