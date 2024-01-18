package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;

	public User validateUser(UserDto userDto) throws UserException {
		User user = modelMapper.map(userDto, User.class);
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
				.orElseThrow(() -> (new UserException("Login unSuccessfull")));
	}

}
