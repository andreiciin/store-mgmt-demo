package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Role;
import com.example.storemgmtdemo.entity.User;

import java.util.List;

public class UserDTO {
	private Integer id;
	private String name;
	private String email;
	private String address;
	private List<Role> roles;
	private List<Order> orders;

	public UserDTO(User user) {
		this.id = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.address = user.getAddress();
		this.roles = user.getRoles();
		this.orders = user.getOrders();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
