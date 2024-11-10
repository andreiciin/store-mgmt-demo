package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty())
			throw new UserNotFoundException("User with following id does not exist: " + id);

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
	public Optional<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isEmpty()) {
			throw new UserNotFoundException("User with the following id does not exist: " + id);
		}

		User user = existingUser.get();
		if (updatedUser.getName() != null) user.setName(updatedUser.getName());
		if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
		if (updatedUser.getAddress() != null) user.setAddress(updatedUser.getAddress());

		userRepository.save(user);

		return Optional.of(user);
	}
}
