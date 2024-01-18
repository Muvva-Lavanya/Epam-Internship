package com.epam;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class Configuration {
	EntityManagerFactory entityManagerFactory;
	@Bean
	public EntityManager getEntityManager()
	{
		entityManagerFactory=Persistence.createEntityManagerFactory("jpa-db");
		return entityManagerFactory.createEntityManager();
	}

}
 