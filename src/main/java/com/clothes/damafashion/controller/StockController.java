package com.clothes.damafashion.controller;

import com.clothes.damafashion.entity.Stock;
import com.clothes.damafashion.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Stock controller.
 */
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    /**
     * Instantiates a new Stock controller.
     *
     * @param stockService the stock service
     */
    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Gets all stocks.
     *
     * @return the all stocks
     */
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.findAll();
    }

    /**
     * Gets stock by product id.
     *
     * @param productId the product id
     * @return the stock by product id
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Stock> getStockByProductId(@PathVariable Long productId) {
        return stockService.findByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update stock response entity.
     *
     * @param productId    the product id
     * @param updatedStock the updated stock
     * @return the response entity
     */
    @PutMapping("/{productId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long productId, @RequestBody Stock updatedStock) {
        return stockService.updateQuantity(productId, updatedStock.getQuantity())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create or update stock stock.
     *
     * @param stock the stock
     * @return the stock
     */
    @PostMapping
    public Stock createOrUpdateStock(@RequestBody Stock stock) {
        return stockService.saveOrUpdate(stock);
    }

    /**
     * Delete stock response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        if (stockService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}