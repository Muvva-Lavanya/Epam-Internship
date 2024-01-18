package com.epam.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserResponseDto addUser(UserRequestDto userRequestDto) {
		log.info("Entered into User : addUser method");
		User user = modelMapper.map(userRequestDto, User.class);
		user = userRepository.save(user);
		log.info("User {} created successfully", user);
		return UserResponseDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).build();
	}

	
	@Override
	public void deleteUser(String username) {
		log.info("Entered into User : deleteUser method");
		userRepository.deleteByUsername(username);
	}

	@Override
	public UserResponseDto getUserByUsername(String username) {
		log.info("Entered into Book : getBookById method");
		return userRepository.findByUsername(username).map(user -> {
			log.info("User {} retrived successfully", user);
			return UserResponseDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).build();
		}).orElseGet(() -> {
			log.info("User with username {} doesnt exists", username);
			return UserResponseDto.builder().timestamp(new Date().toString())
					.developerMessage("User with username " + username + " doesnt exists. Try with different username")
					.httpStatus(HttpStatus.NO_CONTENT).build();
		});
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		log.info("Entered into User : getAllUsers method");
		return userRepository.findAll().stream().map(user->(UserResponseDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).build())).toList();
	}


	@Override
	@Transactional
	public UserResponseDto updateUser(UserRequestDto userRequestDto, String username) {
		log.info("Entered into User : updateUser method");
		return userRepository.findByUsername(username).map(user->{
			user.setEmail(userRequestDto.getEmail());
			user.setName(userRequestDto.getName());
			user.setPassword(userRequestDto.getPassword());
			user.setUsername(userRequestDto.getUsername());
			return UserResponseDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).build();
		}).orElseGet(() -> {
			log.info("User with username {} doesnt exists", username);
			return UserResponseDto.builder().timestamp(new Date().toString())
					.developerMessage("User with username " + username + " doesnt exists. Try with different username")
					.httpStatus(HttpStatus.NO_CONTENT).build();
		});
	}


}
