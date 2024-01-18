package com.epam.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class UserDto {
	@NotBlank(message = "Enter valid username")
	@Pattern(regexp ="^[a-zA-Z0-9]{5,}$",message="userName should be atleast 5 characters long and it should contain only alphanumeric characters")
	private String username;
	@NotBlank(message = "Enter valid name")
	private String name;
	@NotBlank(message = "Enter valid email")
	@Email(message = "Email should be in the format of yyy@epam.com")
	private String email;
	@NotBlank(message = "Enter valid password")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
			message="At least 8 characters long\nContains at least one uppercase letter\nContains at least one lowercase letter\nContains at least one digit\nContains at least one special character (e.g. !, @, #, $, %, ^, &, *)")
	@JsonIgnore
	private String password;
}
