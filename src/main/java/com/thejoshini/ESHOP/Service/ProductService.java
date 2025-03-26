package com.thejoshini.ESHOP.Service;

import com.thejoshini.ESHOP.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductByID(Long Categoryid,Long Productid);
    List<Product> getProductByCategoryId(Long categoryId);
    Product AddProduct(Long Categoryid,Product product);
    List<Product> SearchProduct(String name,Long Categoryid);
    Product updateProduct(Long Categoryid,Long Productid,Product product);
    void deleteProduct(Long Categoryid,Long Productid);
}