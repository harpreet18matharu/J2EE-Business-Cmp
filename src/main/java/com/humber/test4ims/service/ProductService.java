package com.humber.test4ims.service;

import com.humber.test4ims.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
    Product updateStock(Long id, Integer stock);
    long getTotalProducts();
    int getTotalStock();
}
