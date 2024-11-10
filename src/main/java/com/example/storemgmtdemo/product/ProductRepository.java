package com.example.storemgmtdemo.product;

import com.example.storemgmtdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Object> {
	List<Product> findByProductNameContainingIgnoreCase(String productName);
	List<Product> findAllByOrderByPriceAsc();
	List<Product> findAllByOrderByPriceDesc();
}
