package com.example.storemgmtdemo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "order_details")
public class Order {

	@Id
	@GeneratedValue
	private Integer id;
	private Long totalCost;
	private LocalDate orderDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "order_products",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Product> productList;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	public Order() {
	}

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
