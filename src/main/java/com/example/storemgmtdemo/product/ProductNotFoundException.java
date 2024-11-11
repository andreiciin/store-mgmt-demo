package com.example.storemgmtdemo.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String message) { super(message); }
	public ProductNotFoundException(Integer id) {
		super("Product with the following id does not exist: " + id);
	}
}
