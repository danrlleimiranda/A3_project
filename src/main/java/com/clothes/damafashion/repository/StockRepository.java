package com.clothes.damafashion.repository;

import com.clothes.damafashion.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
