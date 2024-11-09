package com.example.storemgmtdemo.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {

	private Integer id;
	private List<Product> productList;
	private Long totalCost;
	private LocalDate orderDate;

	public Order(Integer id, List<Product> productList, Long totalCost, LocalDate orderDate) {
		this.id = id;
		this.productList = productList;
		this.totalCost = totalCost;
		this.orderDate = orderDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
}
