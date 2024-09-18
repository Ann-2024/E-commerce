package com.example.Ecommerce;

import com.example.Ecommerce.Model.Addresses.Pincode;
import com.example.Ecommerce.Service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(EcommerceApplication.class);
		app.setAdditionalProfiles("debug");
		app.run(args);
	}

	}

