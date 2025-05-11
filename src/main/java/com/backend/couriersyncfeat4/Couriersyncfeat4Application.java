package com.backend.couriersyncfeat4;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Couriersyncfeat4Application {

	public static void main(String[] args) {
		SpringApplication.run(Couriersyncfeat4Application.class, args);

	}

	@PostConstruct
	public void init() {
		System.out.println("DB_URL: " + System.getenv("DB_URL"));
	}


}
