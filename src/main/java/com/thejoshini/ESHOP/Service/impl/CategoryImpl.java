package com.thejoshini.ESHOP.Service.impl;

import com.thejoshini.ESHOP.entity.Category;
import com.thejoshini.ESHOP.exception.ResourceNotFoundException;
import com.thejoshini.ESHOP.repository.CategoryRepository;
import com.thejoshini.ESHOP.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category=categoryRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id));

        return category;
    }

    @Override
    public Category saveCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category category1=categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id));
        category1.setName(category.getName());
        category1.setImage(category.getImage());

        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category1=categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id));
        categoryRepository.delete(category1);

    }

}
