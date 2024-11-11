package com.example.storemgmtdemo.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String message) { super(message); }

	public OrderNotFoundException(Integer id) {
		super("Order with the following id does not exist: " + id);
	}
}
