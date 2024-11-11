package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Role;
import com.example.storemgmtdemo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return user;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}

	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		if (updatedUser.getName() != null) user.setName(updatedUser.getName());
		if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
		if (updatedUser.getAddress() != null) user.setAddress(updatedUser.getAddress());

		userRepository.save(user);

		return user;
	}

	@GetMapping("/users/{id}/roles")
	public List<Role> getUserRoles(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return user.getRoles();
	}

	@GetMapping("/users/{id}/orders")
	public List<Order> getUserOrders(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return user.getOrders();
	}
}
