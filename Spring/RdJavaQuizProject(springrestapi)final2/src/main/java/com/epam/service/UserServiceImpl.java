package com.epam.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public User validate(UserDto userDto) throws UserException {
		log.info("User:login");
		User user = modelMapper.map(userDto, User.class);
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
				.orElseThrow(() -> new UserException("Login unSuccessfull"));
	}

	@Override
	public User signUp(UserDto userDto) throws UserException {
		log.info("User:signup");
		User user = modelMapper.map(userDto, User.class);
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserException("User name already exists. Try with different username");
		} else {
			log.info("User saved successfully");
			return userRepository.save(user); 
		}
	}
}
