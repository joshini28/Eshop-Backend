package com.thejoshini.ESHOP.controller;

import com.thejoshini.ESHOP.entity.Category;
import com.thejoshini.ESHOP.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        var data = categoryService.getAllCategory();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        var data=categoryService.getCategoryById(id);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Category> SaveCategory(@RequestBody Category category){
        var data = categoryService.saveCategory(category);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id,@RequestBody Category category){
        var data=categoryService.updateCategory(id,category);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

