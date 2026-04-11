package com.humber.test4ims.repository;

import com.humber.test4ims.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
