package com.epam.service;

import com.epam.dto.UserDto;
import com.epam.entity.User;

interface UserService
{
	public User validate(UserDto userDto);
	public User signUp(UserDto userDto);
}