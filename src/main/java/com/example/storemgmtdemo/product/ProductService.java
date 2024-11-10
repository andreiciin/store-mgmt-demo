package com.example.storemgmtdemo.product;

import com.example.storemgmtdemo.entity.Product;
import com.example.storemgmtdemo.entity.User;
import com.example.storemgmtdemo.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Integer id) {
		return productRepository.findById(id);
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}

	public Product updateProduct(Integer id, Product newProductData) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if (existingProduct.get() == null) {
			throw new ProductNotFoundException("Product with the following id does not exist: " + id);
		}
		return productRepository.findById(id)
				.map(product -> {
					product.setProductName(newProductData.getProductName() == null ? existingProduct.get().getProductName() : newProductData.getProductName());
					product.setPrice(newProductData.getPrice() == null ? existingProduct.get().getPrice() : newProductData.getPrice());
					product.setDescription(newProductData.getDescription() == null ? existingProduct.get().getDescription() : newProductData.getDescription());
					return productRepository.save(product);
				})
				.orElseThrow(() -> new ProductNotFoundException("Product with the following id does not exist: " + id));
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
