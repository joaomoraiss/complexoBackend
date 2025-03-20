package com.example.Complexo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Complexo.repository")
public class ComplexoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplexoApplication.class, args);
	}

}
