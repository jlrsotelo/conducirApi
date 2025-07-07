package com.licencia.conducir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
	    title = "API Establishment",
	    version = "1.0",
	    description = "API documentation for establishment application"
	)
)

@SpringBootApplication
public class ConducirApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConducirApiApplication.class, args);
	}
}