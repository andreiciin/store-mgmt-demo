package com.example.storemgmtdemo.order;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import com.example.storemgmtdemo.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		List<Order> initOrders = orderRepository.findAll();
		for (Order order : initOrders) {
			order.setTotalCost(calculateTotalCost(order));
		}
		return initOrders;
	}

	public Order getOrderById(Integer id) {
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
	}

	public void deleteOrderById(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		order.getProductList().clear();
		orderRepository.save(order);
		orderRepository.deleteById(id);
	}

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public Order updateOrder(Integer id, Order newOrder) {
		Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		return orderRepository.findById(id)
				.map(order -> {
					order.setTotalCost(newOrder.getTotalCost() == null ? existingOrder.getTotalCost() : newOrder.getTotalCost());
					order.setOrderDate(newOrder.getOrderDate() == null ? existingOrder.getOrderDate() : newOrder.getOrderDate());
					order.setProductList(newOrder.getProductList() == null ? existingOrder.getProductList() : newOrder.getProductList());
					order.setUser(newOrder.getUser() == null ? existingOrder.getUser() : newOrder.getUser());
					order.setTotalCost(calculateTotalCost(order));
					return orderRepository.save(order);
				})
				.orElseThrow(() -> new OrderNotFoundException(id));
	}

	public List<Product> getProductsByOrderId(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		return order.getProductList();
	}

	public List<Order> getOrdersByDate(LocalDate date, String sort) {
		Sort sortOrder = sort.equalsIgnoreCase("asc") ? Sort.by("orderDate").ascending() : Sort.by("orderDate").descending();
		return orderRepository.findAll(sortOrder).stream()
				.filter(order -> order.getOrderDate().equals(date))
				.toList();
	}

	private Long calculateTotalCost(Order order) {
		return order.getProductList().stream()
				.mapToLong(Product::getPrice)
				.sum();
	}
}
