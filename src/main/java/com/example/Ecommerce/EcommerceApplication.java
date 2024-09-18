package com.example.Ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		SpringApplication app= new SpringApplication(EcommerceApplication.class);
		app.setAdditionalProfiles("debug");
		app.run(args);
	}

}
