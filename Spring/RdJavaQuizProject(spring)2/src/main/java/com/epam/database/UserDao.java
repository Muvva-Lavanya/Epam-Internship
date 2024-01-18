package com.epam.database;

import java.util.List;

import com.epam.entity.User;

public interface UserDao {
	User save(User user);
	List<User> loadAll();
	void remove(int id);
	boolean checkUser(User user);
}
