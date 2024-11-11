package com.example.storemgmtdemo;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

	@Test
	void testOrderCreation() {
		Product product = new Product( "Credit Rating Margin", 1500, "Maximal credit for user");
		Order order = new Order(List.of(product), 1500L, LocalDate.now());

		assertThat(order.getTotalCost()).isEqualTo(1500L);
		assertThat(order.getProductList()).contains(product);
	}

	@Test
	void testOrderTotalCostCalculation() {
		Product product1 = new Product("House Credit", 1200, "Credit regarding house purchase");
		Product product2 = new Product("Car Credit", 500, "Credit regarding car purchase");

		Order order = new Order();
		order.setProductList(List.of(product1, product2));
		order.setTotalCost((long) (product1.getPrice() + product2.getPrice()));

		assertThat(order.getTotalCost()).isEqualTo(1700);
	}
}
