package com.clothes.damafashion.repository;

import java.util.Optional;

import com.clothes.damafashion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person JPA repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
