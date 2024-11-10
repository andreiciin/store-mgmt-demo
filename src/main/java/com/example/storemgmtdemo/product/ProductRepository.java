package com.example.storemgmtdemo.product;

import com.example.storemgmtdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Object> {
}
