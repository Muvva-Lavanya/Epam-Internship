package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.config.RsaKeyProperties;



@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Configuration {

	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	} 
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
