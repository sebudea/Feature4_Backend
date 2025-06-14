package com.backend.couriersyncfeat4;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Couriersyncfeat4Application {

	public static void main(String[] args) {
		Dotenv.configure().systemProperties().load();

		SpringApplication.run(Couriersyncfeat4Application.class, args);
	}
}