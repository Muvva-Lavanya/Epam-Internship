package com.epam.database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
@Repository
public class UserDaoJpaImpl implements UserDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa-db");;
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	@Override
	public User save(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user; 
	}

	@Override
	public List<User> loadAll() {
		TypedQuery<User>query=entityManager.createQuery("select user from User",User.class);
		return query.getResultList();  
	}

	@Override
	public void remove(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit(); 

	} 
	@Override
	public boolean checkUser(User user) 
	{
		String queryString = "SELECT u FROM User u WHERE u.userType = '"+user.getUserType()+"'AND u.username= '" +user.getUsername() + "' AND u.password = '" + user.getPassword() + "'";
		List<User> query = entityManager.createQuery(queryString, User.class).getResultList();
		return(!query.isEmpty());


	}

}
