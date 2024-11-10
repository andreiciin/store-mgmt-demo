package com.example.storemgmtdemo.order;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Object> {
	List<Order> findAllByProductListContaining(Product product);
}
