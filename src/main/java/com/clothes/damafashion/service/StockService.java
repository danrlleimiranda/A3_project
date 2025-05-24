package com.clothes.damafashion.service;

import com.clothes.damafashion.entity.Stock;
import com.clothes.damafashion.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // Listar todos os estoques
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    // Buscar o estoque de um produto pelo ID
    public Optional<Stock> findByProductId(Long productId) {
        return stockRepository.findById(productId);
    }

    // Criar ou atualizar o estoque de um produto
    public Stock saveOrUpdate(Stock stock) {
        return stockRepository.save(stock);
    }

    // Atualizar o estoque do produto pelo ID do produto
    public Optional<Stock> updateQuantity(Long productId, int quantity) {
        return stockRepository.findById(productId).map(stock -> {
            stock.setQuantity(quantity);
            return stockRepository.save(stock);
        });
    }

    // Deletar estoque de um produto pelo ID
    public boolean delete(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;
    }
}