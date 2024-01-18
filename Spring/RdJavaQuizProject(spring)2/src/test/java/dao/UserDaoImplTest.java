package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

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
		User savedUser=userDaoJpaImpl.save(user);
		Mockito.verify(entityManager).persist(savedUser);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(user, savedUser); 
	}
	@Test
	void testDeleteUser()
	{
		User user=new User("user","Lavanya","Lavanya@123");
		user.setId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(User.class, user.getId())).thenReturn(user);
		userDaoJpaImpl.remove(1);
		Mockito.verify(entityManager).find(User.class, user.getId());
		Mockito.verify(entityManager).remove(user);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		
	} 
	@Test
	void testAllUsers()
	{
		User user1=new User("user","Lavanya","Lavanya@123");
		User user2=new User("user","Sai","Sai@123");
		List<User> usersList=Arrays.asList(user1,user2);
		Mockito.when(entityManager.createQuery("select user from User",User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(usersList);
		List<User> retrivedUsers=userDaoJpaImpl.loadAll();
		Mockito.verify(query).getResultList();
		assertEquals(usersList, retrivedUsers);
	}
//	void checkUser()
//	{
//		User user=new User("user","Lavanya","Lavanya@123"); 
//		String queryString = "SELECT u FROM User u WHERE u.userType = '"+user.getUserType()+"'AND u.username= '" +user.getUsername() + "' AND u.password = '" + user.getPassword() + "'";
//	}
	
	
}
