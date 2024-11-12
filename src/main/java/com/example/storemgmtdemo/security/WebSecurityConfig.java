package com.example.storemgmtdemo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Bean
	public UserDetailsService userDetailsService() {
		logger.debug("Configuring UserDetailsService with CustomUserDetailsService");
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		logger.debug("not  like BCrypt");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests()
//				.requestMatchers("/users/**").hasRole("ADMIN")
//				.requestMatchers("/users/{id}", "/users/{id}/orders").hasAnyRole("ADMIN", "USER")
//				.requestMatchers("/orders/**").hasAnyRole("ADMIN", "OFFICER")
//				.requestMatchers("/products/**").permitAll()
				.requestMatchers("/users/**","/login", "/register", "/products/**","/orders/**").permitAll()
				.anyRequest().authenticated()
				.and()
//				.httpBasic()
//				.and()
//				.formLogin().disable()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout().permitAll();

		http.authenticationProvider(authenticationProvider());
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
