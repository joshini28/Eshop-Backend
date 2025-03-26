package com.thejoshini.ESHOP.Service;

import com.thejoshini.ESHOP.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Long id);
    Category saveCategory(Category category);
    Category updateCategory(Long id,Category category);
    void deleteCategory(Long id);

}