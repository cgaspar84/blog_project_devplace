package com.fidelitytechnologies.training.blogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class FidelityBlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FidelityBlogAppApplication.class, args);
	}

}
