package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Role;
import com.example.storemgmtdemo.entity.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<UserDTO> retrieveAllUsers() {
		return userService.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/users/{id}")
	public UserDTO retrieveUser(@PathVariable int id) {
		User user = userService.getUserById(id);
		return new UserDTO(user);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		userService.createUser(user);
	}

	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		return userService.updateUser(id, updatedUser);
	}

	@GetMapping("/users/{id}/roles")
	public List<Role> getUserRoles(@PathVariable int id) {
		return userService.getUserByRoles(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.id")
	@GetMapping("/users/{id}/orders")
	public List<Order> getUserOrders(@PathVariable int id, @AuthenticationPrincipal User authenticatedUser) {
		if (authenticatedUser == null || id != authenticatedUser.getUserId()) {
			throw new AccessDeniedException("You cannot access orders of other users.");
		}

		return userService.getUserByOrders(id);
	}
}
