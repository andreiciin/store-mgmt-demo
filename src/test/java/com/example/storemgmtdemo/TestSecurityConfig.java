package com.example.storemgmtdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {

	@Bean
	public SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests().anyRequest().permitAll();
		return http.build();
	}
}
