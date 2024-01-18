package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book Microservices",description = "Documentation For Book Microservices"))
public class LibraryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMicroserviceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}