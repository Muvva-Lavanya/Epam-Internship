package com.epam.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	private String userType;
	@NonNull
	private String username;
	@NonNull
	private String password;
	
	
}
