package com.example.storemgmtdemo;

import com.example.storemgmtdemo.entity.Order;
import com.example.storemgmtdemo.entity.Role;
import com.example.storemgmtdemo.entity.User;
import com.example.storemgmtdemo.user.UserRepository;
import com.example.storemgmtdemo.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void testUserCreation() {
		Role role = new Role("USER", "Standard user with limited access");
		Order order = new Order();
		User user = new User("Toni Demo", "toni.demo@hotmail.com", "passwd", "Tonis Street no. 16", List.of(role), List.of(order));

		assertThat(user.getName()).isEqualTo("Toni Demo");
		assertThat(user.getEmail()).isEqualTo("toni.demo@hotmail.com");
		assertThat(user.getAddress()).isEqualTo("Tonis Street no. 16");
		assertThat(user.getRoles()).contains(role);
		assertThat(user.getOrders()).contains(order);
	}

	@Test
	void testUserRoleAssignment() {
		User user = new User();
		Role adminRole = new Role("ADMIN", "Administrator access");
		user.setRoles(List.of(adminRole));

		assertThat(user.getRoles()).hasSize(1);
		assertThat(user.getRoles().get(0).getRoleName()).isEqualTo("ADMIN");
	}

	@Test
	public void testPasswordIsEncoded() {
		User user = new User();
		user.setPassword("plainTextPassword");
		userService.createUser(user);

		User savedUser = userRepository.findById(user.getUserId()).orElse(null);
		assertNotNull(savedUser);
		assertNotEquals("plainTextPassword", savedUser.getPassword());
	}
}
