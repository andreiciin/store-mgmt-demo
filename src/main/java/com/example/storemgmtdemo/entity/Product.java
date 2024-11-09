package com.example.storemgmtdemo.entity;

public class Product {

	private Integer id;
	private String productName;
	private Integer price;
	private String description;

	public Product(Integer id, String productName, Integer price, String description) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
