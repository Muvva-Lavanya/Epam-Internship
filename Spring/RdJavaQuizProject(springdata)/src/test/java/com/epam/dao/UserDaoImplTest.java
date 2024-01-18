package com.epam.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.UserDaoJpaImpl;
import com.epam.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
 class UserDaoImplTest {
	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<User> query;
	@Mock
	private EntityTransaction transaction;
	@InjectMocks
	UserDaoJpaImpl userDaoJpaImpl;
	@Test
	void testSaveUser()
	{
		User user=new User("user","Lavanya","Lavanya@123"); 
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(entityManager).persist(user);
		User savedUser=userDaoJpaImpl.saveUser(user);
		Mockito.verify(entityManager).persist(savedUser);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(user, savedUser); 
	}
	@Test
	void testValidateUser()
	{
		User user=new User("user","Lavanya","Lavanya@123"); 
		Mockito.when(entityManager.createQuery( "SELECT u FROM User u WHERE u.userType = :userType AND u.username =:userName AND u.password = :userPassword",User.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);
		boolean checkUser=userDaoJpaImpl.validateUser("user","Lavanya","Lavanya@123");
		Mockito.verify(entityManager).createQuery( "SELECT u FROM User u WHERE u.userType = :userType AND u.username =:userName AND u.password = :userPassword",User.class);
		Mockito.verify(query).getSingleResult();
		assertEquals(true,checkUser);
	}
	@Test
	void testValidateFalseUser() 
	{
		Mockito.when(entityManager.createQuery( "SELECT u FROM User u WHERE u.userType = :userType AND u.username =:userName AND u.password = :userPassword",User.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenThrow(new NoResultException());
		boolean checkUser=userDaoJpaImpl.validateUser("user","Lavanya","Lavanya@123");
		Mockito.verify(entityManager).createQuery( "SELECT u FROM User u WHERE u.userType = :userType AND u.username =:userName AND u.password = :userPassword",User.class);
		Mockito.verify(query).getSingleResult();
		assertEquals(false,checkUser);
		
	}
	
	@Test
	void testCheckUsername()
	{
		User user=new User("user","Lavanya","Lavanya@123"); 
		Mockito.when(entityManager.createQuery("select user from User user where user.username= :userName",User.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);
		Optional<User> checkUser=userDaoJpaImpl.checkUserExists("Lavanya");
		Mockito.verify(entityManager).createQuery("select user from User user where user.username= :userName",User.class);
		Mockito.verify(query).getSingleResult();
		assertEquals(user,checkUser.get());
	}
	
	@Test
	void testCheckExistingUsername()
	{
		User user=new User("user","Lavanya","Lavanya@123"); 
		Mockito.when(entityManager.createQuery("select user from User user where user.username= :userName",User.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);
		Optional<User> checkUser=userDaoJpaImpl.checkUserExists("Lavanya");
		Mockito.verify(entityManager).createQuery("select user from User user where user.username= :userName",User.class);
		Mockito.verify(query).getSingleResult();
		assertEquals(user,checkUser.get());
	}
	
	@Test 
	void testCheckUnExistingUsername()
	{
		Mockito.when(entityManager.createQuery("select user from User user where user.username= :userName",User.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenThrow(new NoResultException());
		Optional<User> checkUser=userDaoJpaImpl.checkUserExists("Lavanya1");
		Mockito.verify(entityManager).createQuery("select user from User user where user.username= :userName",User.class);
		Mockito.verify(query).getSingleResult();
		assertTrue(checkUser.isEmpty()); 
	}
	
}
