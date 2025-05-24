package com.clothes.damafashion.repository;

import com.clothes.damafashion.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
