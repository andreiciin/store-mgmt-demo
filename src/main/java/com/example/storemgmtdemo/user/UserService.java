package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Product;
import com.example.storemgmtdemo.entity.Role;
import com.example.storemgmtdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
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
		encodePassword(user);
		return userRepository.save(user);
	}

	public User updateUser(Integer id, User updatedUser) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		if (updatedUser.getName() != null) user.setName(updatedUser.getName());
		if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
		if (updatedUser.getAddress() != null) user.setAddress(updatedUser.getAddress());
		if (updatedUser.getPassword() != null) {
			encodePassword(user);
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

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
}
