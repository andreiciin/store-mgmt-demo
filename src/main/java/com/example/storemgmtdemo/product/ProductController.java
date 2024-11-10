package com.example.storemgmtdemo.product;

import com.example.storemgmtdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productService.getProductById(id);
	}

	@PostMapping("/products")
	public void createProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product newProductData) {
		return productService.updateProduct(id, newProductData);
	}

	@GetMapping("/products/search")
	public List<Product> getProductsByName(@RequestParam String name) {
		return productService.getProductsByName(name);
	}

	@GetMapping("/products/sort/priceAsc")
	public List<Product> getProductsByPriceAsc() {
		return productService.getProductsByPriceAsc();
	}

	@GetMapping("/products/sort/priceDesc")
	public List<Product> getProductsByPriceDesc() {
		return productService.getProductsByPriceDesc();
	}
}
