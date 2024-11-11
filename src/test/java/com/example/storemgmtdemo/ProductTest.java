package com.example.storemgmtdemo;

import com.example.storemgmtdemo.entity.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

	@Test
	void testProductCreation() {
		Product product = new Product("Small investment loan", 20000, "Loan regarding investments");

		assertThat(product.getProductName()).isEqualTo("Small investment loan");
		assertThat(product.getPrice()).isEqualTo(20000);
		assertThat(product.getDescription()).isEqualTo("Loan regarding investments");
	}

	@Test
	void testUpdateProductPrice() {
		Product product = new Product();
		product.setPrice(1500);

		assertThat(product.getPrice()).isEqualTo(1500);
	}
}
