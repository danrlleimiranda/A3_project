package com.clothes.damafashion.service;

import com.clothes.damafashion.entity.Stock;
import com.clothes.damafashion.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Stock service.
 */
@Service
public class StockService {

    private final StockRepository stockRepository;

    /**
     * Instantiates a new Stock service.
     *
     * @param stockRepository the stock repository
     */
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    /**
     * Find by product id optional.
     *
     * @param productId the product id
     * @return the optional
     */
    public Optional<Stock> findByProductId(Long productId) {
        return stockRepository.findById(productId);
    }

    /**
     * Save or update stock.
     *
     * @param stock the stock
     * @return the stock
     */
    public Stock saveOrUpdate(Stock stock) {
        return stockRepository.save(stock);
    }

    /**
     * Update quantity optional.
     *
     * @param productId the product id
     * @param quantity  the quantity
     * @return the optional
     */
    public Optional<Stock> updateQuantity(Long productId, int quantity) {
        return stockRepository.findById(productId).map(stock -> {
            stock.setQuantity(quantity);
            return stockRepository.save(stock);
        });
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean delete(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;
    }
}