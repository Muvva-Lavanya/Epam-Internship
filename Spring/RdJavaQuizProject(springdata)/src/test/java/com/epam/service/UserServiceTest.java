package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.entity.Question;
import com.epam.entity.Quiz;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@InjectMocks
	UserService userService;
	Quiz quiz;

	@BeforeEach
	public void setUp() {
		quiz = (new Quiz(1, "java", 2,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "2"))));
	}

	@Test
	void testCountMarks() {
		quiz.setId(1);
		int expectedMarks=2;
		Mockito.when(quizLibraryServiceImpl.viewQuizById(1)).thenReturn(quiz);
		int actualMarks=userService.countMarks(1,Arrays.asList("1","3"));
		assertEquals(expectedMarks,actualMarks);
		
	}
}
