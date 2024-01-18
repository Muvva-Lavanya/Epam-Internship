package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;

@Service
public class SignUpService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	public User signUpService(UserDto userDto) throws UserException {
		User user=modelMapper.map(userDto,User.class);
		if(userRepository.findByUsername(user.getUsername()).isPresent()) { 
			throw new UserException("User name already exists. Try with different username");
		}else {
			return userRepository.save(user);
		} 
	}
}
 