package com.example.storemgmtdemo.order;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with the following id does not exist: " + id));
	}

	public void deleteOrderById(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with the following id does not exist: " + id));
		order.getProductList().clear();
		orderRepository.save(order);
		orderRepository.deleteById(id);
	}

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public Order updateOrder(Integer id, Order newOrder) {
		Optional<Order> existingOrder = orderRepository.findById(id);
		if (existingOrder.get() == null ) {
			throw new OrderNotFoundException("Order with the following id does not exist: " + id);
		}
		return orderRepository.findById(id)
				.map(order -> {
					order.setTotalCost(newOrder.getTotalCost() == null ? existingOrder.get().getTotalCost() : newOrder.getTotalCost());
					order.setOrderDate(newOrder.getOrderDate() == null ? existingOrder.get().getOrderDate() : newOrder.getOrderDate());
					order.setProductList(newOrder.getProductList() == null ? existingOrder.get().getProductList() : newOrder.getProductList());
					order.setTotalCost(calculateTotalCost(order));
					return orderRepository.save(order);
				})
				.orElseThrow(() -> new OrderNotFoundException("Order with the following id does not exist: " + id));
	}

	public List<Product> getProductsByOrderId(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with the following id does not exist: " + id));
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
