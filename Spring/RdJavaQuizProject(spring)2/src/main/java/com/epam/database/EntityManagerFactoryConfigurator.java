package com.epam.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryConfigurator {
	private EntityManagerFactoryConfigurator()
	{
		
	}
	private static class Holder
	{
		static final EntityManagerFactory factoryInstance=Persistence.createEntityManagerFactory("jpa-db");
	}
	public static EntityManagerFactory getGlobalInstance()
	{
		return Holder.factoryInstance;
	}
}
