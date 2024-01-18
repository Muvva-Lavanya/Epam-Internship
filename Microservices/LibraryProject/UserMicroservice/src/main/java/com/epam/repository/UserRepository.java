package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.entity.User;
@Transactional
public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	void deleteByUsername(String username);

}
