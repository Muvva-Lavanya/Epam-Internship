package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	public Optional<User> findByUsernameAndPassword(String username,String password);
	public Optional<User> findByUsername(String username);

}
