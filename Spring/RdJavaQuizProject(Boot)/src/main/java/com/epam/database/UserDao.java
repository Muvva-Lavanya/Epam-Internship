package com.epam.database;


import java.util.Optional;

import com.epam.entity.User;

public interface UserDao {
	User saveUser(User user);
	Optional<User> checkUserExists(String userName);
	boolean validateUser(User user);
}
