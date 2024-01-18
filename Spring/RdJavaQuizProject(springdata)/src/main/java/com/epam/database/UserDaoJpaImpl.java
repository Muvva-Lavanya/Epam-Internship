package com.epam.database;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
@Repository
public class UserDaoJpaImpl implements UserDao {
	@Autowired
	EntityManager entityManager;
	@Override
	public User saveUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user; 
	}
	@Override
	public boolean validateUser(String userType,String username,String password) 
	{
		TypedQuery<User> query=entityManager.createQuery( "SELECT u FROM User u WHERE u.userType = :userType AND u.username =:userName AND u.password = :userPassword",User.class);
		query.setParameter("userType",userType);
		query.setParameter("userName",username);
		query.setParameter("userPassword",password); 
		Optional<User> checkUser;
		try {
			checkUser=Optional.ofNullable(query.getSingleResult());
		}
		catch(NoResultException e)
		{
			checkUser=Optional.empty();
		}
		return checkUser.isPresent();
	}
	@Override
	public Optional<User> checkUserExists(String userName)
	{
		TypedQuery<User> query=entityManager.createQuery("select user from User user where user.username= :userName",User.class);
		query.setParameter("userName",userName);
		Optional<User> user;
		try {
			user = Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) { 
			user = Optional.empty();
		}
		return user;
	}

}
