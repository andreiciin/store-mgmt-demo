package com.example.storemgmtdemo.order;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable Integer id) {
		return orderService.getOrderById(id);
	}

	@PostMapping("/orders")
	public Order createOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}

	@DeleteMapping("/orders/{id}")
	public void deleteOrderById(@PathVariable Integer id) {
		orderService.deleteOrderById(id);
	}

	@PutMapping("/orders/{id}")
	public Order updateOrder(@PathVariable Integer id, @RequestBody Order newOrder) {
		return orderService.updateOrder(id, newOrder);
	}

	@GetMapping("/orders/{id}/products")
	public List<Product> getProductsByOrderId(@PathVariable Integer id) {
		return orderService.getProductsByOrderId(id);
	}

	@GetMapping("/orders/filter")
	public List<Order> getOrdersByDate(
			@RequestParam LocalDate date,
			@RequestParam(defaultValue = "asc") String sort) {
		return orderService.getOrdersByDate(date, sort);
	}
}
