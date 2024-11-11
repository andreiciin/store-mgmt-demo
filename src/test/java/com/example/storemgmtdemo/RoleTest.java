package com.example.storemgmtdemo;

import com.example.storemgmtdemo.entity.Role;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleTest {

	@Test
	void testRoleCreation() {
		Role role = new Role("ADMIN", "Administrator with full access");

		assertThat(role.getRoleName()).isEqualTo("ADMIN");
		assertThat(role.getRoleDescription()).isEqualTo("Administrator with full access");
	}
}
