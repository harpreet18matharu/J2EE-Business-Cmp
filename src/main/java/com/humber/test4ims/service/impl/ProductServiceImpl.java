package com.humber.test4ims.service.impl;

import com.humber.test4ims.entity.Product;
import com.humber.test4ims.repository.ProductRepository;
import com.humber.test4ims.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product updateStock(Long id, Integer stock) {
        Product product = getProductById(id);
        product.setStock(stock);
        return productRepository.save(product);
    }

    @Override
    public long getTotalProducts() {
        return productRepository.count();
    }

    @Override
    public int getTotalStock() {
        return productRepository.findAll()
                .stream()
                .mapToInt(Product::getStock)
                .sum();
    }
}
