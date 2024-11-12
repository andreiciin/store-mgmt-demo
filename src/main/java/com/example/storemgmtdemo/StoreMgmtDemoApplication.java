package com.example.storemgmtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StoreMgmtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreMgmtDemoApplication.class, args);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String encodedPassword1 = encoder.encode("pass");
		String encodedPassword2 = encoder.encode("pass456");
		String encodedPassword3 = encoder.encode("pass789");
		String encodedPassword4 = encoder.encode("pass317");

		System.out.println("Encoded Passwords:");
		System.out.println("Gimi: " + encodedPassword1);
		System.out.println("Nina: " + encodedPassword2);
		System.out.println("Doru: " + encodedPassword3);
		System.out.println("Moni: " + encodedPassword4);
	}

}
