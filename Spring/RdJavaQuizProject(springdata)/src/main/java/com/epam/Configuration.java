package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class Configuration {
	
	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}
//	EntityManagerFactory entityManagerFactory;
//	@Bean
//	public EntityManager getEntityManager()
//	{
//		entityManagerFactory=Persistence.createEntityManagerFactory("jpa-db");
//		return entityManagerFactory.createEntityManager();
//	}
	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}

}
 