package com.clothes.damafashion.repository;

import com.clothes.damafashion.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Stock repository.
 */
public interface StockRepository extends JpaRepository<Stock, Long> {
}
