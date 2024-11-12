package com.example.storemgmtdemo;
import com.example.storemgmtdemo.entity.User;
import com.example.storemgmtdemo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setup() {
		userRepository.deleteAll();
	}

	@Test
	void testCreateUser() throws Exception {
		User user = new User("Gimi", "gimi.h@yahoo.com", "pass123", "Gimi Street 11");
		String userJson = objectMapper.writeValueAsString(user);

		ResultActions result = mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson));

		result.andExpect(status().isOk());
		assertThat(userRepository.findAll()).hasSize(1);
	}

	@Test
	void testRetrieveUserById() throws Exception {
		User user = new User(3, "Nina", "nina.p@gmail.com", "pass456", "Nina Street 12");
		User savedUser = userRepository.save(user);

		mockMvc.perform(get("/users/" + savedUser.getUserId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Nina"))
				.andExpect(jsonPath("$.email").value("nina.p@gmail.com"));
	}

	@Test
	void testRetrieveAllUsers() throws Exception {
		userRepository.save(new User(1, "Gimi", "gimi.h@yahoo.com", "pass123", "Gimi Street 11"));
		userRepository.save(new User(2, "Doru", "doru.m@hotmail.com", "pass789", "Doru Street 13"));

		mockMvc.perform(get("/users")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	void testUpdateUser() throws Exception {
		User user = new User(4, "Gimi", "gimi.h@yahoo.com", "pass123", "Gimi Street 11");
		User savedUser = userRepository.save(user);

		User updatedUser = new User(savedUser.getUserId(), "Updated Gimi", "gimi.updated@yahoo.com", "newpass123", "Updated Street 11");
		String updatedUserJson = objectMapper.writeValueAsString(updatedUser);

		mockMvc.perform(put("/users/" + savedUser.getUserId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(updatedUserJson))
				.andExpect(status().isOk());

		User retrievedUser = userRepository.findById(savedUser.getUserId()).get();
		assertThat(retrievedUser.getName()).isEqualTo("Updated Gimi");
		assertThat(retrievedUser.getEmail()).isEqualTo("gimi.updated@yahoo.com");
	}

	@Test
	void testDeleteUser() throws Exception {
		User user = new User(5, "Doru", "doru.m@hotmail.com", "pass789", "Doru Street 13");
		User savedUser = userRepository.save(user);

		mockMvc.perform(delete("/users/" + savedUser.getUserId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		assertThat(userRepository.existsById(savedUser.getUserId())).isFalse();
	}
}
