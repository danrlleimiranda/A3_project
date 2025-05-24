package com.clothes.damafashion.repository;

import java.util.Optional;

import com.clothes.damafashion.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person JPA repository.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findByUsername(String username);
}
