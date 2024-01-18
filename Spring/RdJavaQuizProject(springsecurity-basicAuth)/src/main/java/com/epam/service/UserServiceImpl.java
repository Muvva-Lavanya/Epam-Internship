package com.epam.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	Logger logger = LogManager.getLogger(UserServiceImpl.class);
	@Override
	public User validate(UserDto userDto) throws UserException {
		logger.info("User:login");
		User user = modelMapper.map(userDto, User.class);
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
				.orElseThrow(() -> new UserException("Login unSuccessfull"));
	}

	@Override
	public User signUp(UserDto userDto) throws UserException {
		logger.info("User:signup");
		User user = modelMapper.map(userDto, User.class);
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserException("User name already exists. Try with different username");
		} else {
			logger.info("User saved successfully");
			return userRepository.save(user); 
		}
	}
}
