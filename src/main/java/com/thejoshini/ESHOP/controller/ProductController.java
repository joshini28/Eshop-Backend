package com.thejoshini.ESHOP.controller;

import com.thejoshini.ESHOP.entity.Product;
import com.thejoshini.ESHOP.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        var data= productService.getAllProducts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId,@PathVariable("categoryId") Long CategoryId){
        var data= productService.getProductByID(CategoryId,productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductByCategoryId(@PathVariable("categoryId") Long Categoryid){
        var data=productService.getProductByCategoryId(Categoryid);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @PostMapping("/category/{categoryId}/products")
    public ResponseEntity<Product> AddProduct(@PathVariable("categoryId") Long CategoryId,@RequestBody Product product){
        var data=productService.AddProduct(CategoryId,product);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/productsbyname/{name}")
    public ResponseEntity<List<Product>> GetProductByName(@PathVariable("name") String ProductName,@PathVariable("categoryId") Long categoryId){
        var data=productService.SearchProduct(ProductName,categoryId);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @PutMapping("/category/{categoryId}/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
                                                 @PathVariable("categoryId") Long CategoryId,
                                                 @RequestBody Product product
    ){
        var data = productService.updateProduct(CategoryId,productId,product);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @DeleteMapping("/category/{categoryId}/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId,
                                              @PathVariable("categoryId") Long CategoryId){
        productService.deleteProduct(CategoryId,productId);
        return ResponseEntity.noContent().build();
    }


}
