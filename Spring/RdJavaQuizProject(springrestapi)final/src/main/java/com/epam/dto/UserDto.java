package com.epam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@Pattern(regexp = "^(?i)(admin|user)$",message="User type should be either admin or user")
	private String userType;
	@NotBlank(message="Username should not be empty")
	private String username;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
			message="At least 8 characters long\nContains at least one uppercase letter\nContains at least one lowercase letter\nContains at least one digit\nContains at least one special character (e.g. !, @, #, $, %, ^, &, *)")
	private String password;
	
	
}
