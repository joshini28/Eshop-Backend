package com.thejoshini.ESHOP.repository;

import com.thejoshini.ESHOP.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByname(String name);
    List<Product> findByCategoryId(Long categoryId);
}
