package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import com.example.storemgmtdemo.entity.Role;
import com.example.storemgmtdemo.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		users.forEach(user ->
				user.getOrders().forEach(order ->
						order.setTotalCost(calculateTotalCost(order))
				)
		);
		return users;
	}

	public User getUserById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return getUserWithTotalCost(user);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User updateUser(Integer id, User updatedUser) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		if (updatedUser.getName() != null) user.setName(updatedUser.getName());
		if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
		if (updatedUser.getAddress() != null) user.setAddress(updatedUser.getAddress());
		if (updatedUser.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
		}

		return userRepository.save(user);
	}

	public List<Role> getUserByRoles(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return user.getRoles();
	}

	public List<Order> getUserByOrders(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		user.getOrders().forEach(order -> order.setTotalCost(calculateTotalCost(order)));
		return user.getOrders();
	}

	private User getUserWithTotalCost(User user) {
		user.getOrders().forEach(order -> order.setTotalCost(calculateTotalCost(order)));
		return user;
	}

	private Long calculateTotalCost(Order order) {
		return order.getProductList().stream()
				.mapToLong(Product::getPrice)
				.sum();
	}
}
