package com.epam.entity;


import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	private String userType;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(userType.split(","))
				.map(SimpleGrantedAuthority::new ).toList();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	
}
