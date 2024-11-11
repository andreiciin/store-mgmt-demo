package com.example.storemgmtdemo.product;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import com.example.storemgmtdemo.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Integer id) {
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Integer id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		List<Order> ordersWithProduct = orderRepository.findAllByProductListContaining(product);

		for (Order order : ordersWithProduct) {
			order.getProductList().remove(product);
			orderRepository.save(order);
		}

		productRepository.deleteById(id);
	}

	public Product updateProduct(Integer id, Product newProductData) {
		Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		return productRepository.findById(id)
				.map(product -> {
					product.setProductName(newProductData.getProductName() == null ? existingProduct.getProductName() : newProductData.getProductName());
					product.setPrice(newProductData.getPrice() == null ? existingProduct.getPrice() : newProductData.getPrice());
					product.setDescription(newProductData.getDescription() == null ? existingProduct.getDescription() : newProductData.getDescription());
					return productRepository.save(product);
				})
				.orElseThrow(() -> new ProductNotFoundException(id));
	}

	public List<Product> getProductsByName(String name) {
		return productRepository.findByProductNameContainingIgnoreCase(name);
	}

	public List<Product> getProductsByPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	public List<Product> getProductsByPriceDesc() {
		return productRepository.findAllByOrderByPriceDesc();
	}
}
