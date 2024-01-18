package com.epam.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import com.epam.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	public User() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String userType;
	public User(String userType, String username, String password) {
		this.userType = userType;
		this.username = username;
		this.password = password;
	}
	private String username;
	private String password;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public User(UserDto userDto) {
		this.userType=userDto.getUserType();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userType=" + userType + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
