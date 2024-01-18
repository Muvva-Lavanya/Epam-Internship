package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexception.QuizException;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.repository.QuizRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
	private QuizRepository quizRepository;
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
		Mockito.when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
		long actualMarks=userService.countMarks(1,Arrays.asList("1","3"));
		assertEquals(expectedMarks,actualMarks);
		
	}
	@Test
	void testCountMarksFailureCase() {
		quiz.setId(1);
		Mockito.when(quizRepository.findById(100)).thenReturn(Optional.empty());
		List<String> answers=Arrays.asList("1","3");
		assertThrows(QuizException.class,()->userService.countMarks(100,answers));
	}
}
