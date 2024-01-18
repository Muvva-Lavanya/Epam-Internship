package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto{
	private int id;
	@Size(min=5,max=30,message="Title should be 5 to 30 characters")
	@NonNull
	private String title;
	@Size(min=2,message="There should be atleast two options")
	@NonNull
	private List<String> options=new ArrayList<>(); 
	@Pattern(regexp = "^(?i)(Easy|Medium|Hard)$",message="Difficulty level should be one among Easy,Medium and Hard")
	@NonNull
	private String difficultyLevel;
	@NotBlank(message="Tag should not be empty")
	@NonNull
	private String tag;
	@NotBlank(message="Answer should not be empty")
	@NonNull
	private String answer;
}