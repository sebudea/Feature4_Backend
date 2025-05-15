package com.backend.couriersyncfeat4;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class Couriersyncfeat4Application {

	public static void main(String[] args) {
		SpringApplication.run(Couriersyncfeat4Application.class, args);
		byte[] randomBytes = new byte[32];
		new SecureRandom().nextBytes(randomBytes);
		String secret = Base64.getEncoder().encodeToString(randomBytes);
		System.out.println("JWT Secret Base64: " + secret);
	}

}
