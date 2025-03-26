package com.thejoshini.ESHOP.Service.impl;

import com.thejoshini.ESHOP.Service.ProductService;
import com.thejoshini.ESHOP.entity.Category;
import com.thejoshini.ESHOP.entity.Product;
import com.thejoshini.ESHOP.exception.ResourceNotFoundException;
import com.thejoshini.ESHOP.repository.CategoryRepository;
import com.thejoshini.ESHOP.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImpl implements ProductService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRespository;
    @Override
    public List<Product> getAllProducts() {
        return productRespository.findAll();
    }

    @Override
    public Product getProductByID(Long Categoryid, Long productid) {
        Category category=categoryRepository
                .findById(Categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",Categoryid));
        Product product=productRespository
                .findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ProductId",productid));
        if(!product.getCategory().getId().equals(category.getId())){
            throw new RuntimeException("product does not belong to this category");
        }
        return product;
    }


    @Override
//    @Transactional
    public List<Product> getProductByCategoryId(Long categoryId) {
        return productRespository.findByCategoryId(categoryId);
    }

    @Override
    public Product AddProduct(Long Categoryid, Product product) {
        Category category=categoryRepository
                .findById(Categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",Categoryid));
        product.setCategory(category);

        return productRespository.save(product);


    }

    @Override
    public List<Product> SearchProduct(String name, Long Categoryid) {
        Category category=categoryRepository
                .findById(Categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",Categoryid));
        List<Product> product= (List<Product>) productRespository
                .findByname(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ProductId",name));
        if(!product.get(0).getCategory().getId().equals(category.getId())){
            throw new RuntimeException("product does not belong to this category");
        }
        return product;
    }

    @Override
    public Product updateProduct(Long Categoryid, Long Productid, Product product) {
        Category category=categoryRepository
                .findById(Categoryid)
                .orElseThrow(() ->  new ResourceNotFoundException("Category","CategoryId",Categoryid));
        Product product1=productRespository
                .findById(Productid)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ProductId",Productid));
        if(!product1.getCategory().getId().equals(category.getId())){
            throw new RuntimeException("product does not belong to this category");
        }

        product1.setImage(product.getImage());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        return productRespository.save(product1);
    }

    @Override
    public void deleteProduct(Long Categoryid, Long Productid) {
        Category category=categoryRepository
                .findById(Categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",Categoryid));
        Product product1=productRespository
                .findById(Productid)
                .orElseThrow(() ->  new ResourceNotFoundException("Product","ProductId",Productid));
        if(!product1.getCategory().getId().equals(category.getId())){
            throw new RuntimeException("product does not belong to this category");
        }
        productRespository.delete(product1);

    }
}
