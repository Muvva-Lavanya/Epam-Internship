package com.epam.dto;

import java.util.List;

import com.epam.entity.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@Size(min=1,max=2)
	private List<Role> role;
	@NotBlank(message="Username should not be empty")
	private String username;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
			message="At least 8 characters long\nContains at least one uppercase letter\nContains at least one lowercase letter\nContains at least one digit\nContains at least one special character (e.g. !, @, #, $, %, ^, &, *)")
	private String password;
	
	
}
