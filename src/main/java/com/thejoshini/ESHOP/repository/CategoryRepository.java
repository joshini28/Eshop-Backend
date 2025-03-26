package com.thejoshini.ESHOP.repository;

import com.thejoshini.ESHOP.entity.Category;
import com.thejoshini.ESHOP.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}

