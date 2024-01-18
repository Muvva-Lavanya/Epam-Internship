package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
import org.modelmapper.ModelMapper;

import com.epam.customexception.QuestionException;
import com.epam.customexception.QuizException;
import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;

@ExtendWith(MockitoExtension.class)
class QuizLibraryServiceTest {
	@Mock
	private QuizRepository quizRepository;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private QuestionRepository questionRepository;
	
	@InjectMocks
	private QuizLibraryServiceImpl quizLibraryServiceImpl;
	List<Quiz> quizList = new ArrayList<>();
	List<QuizDto> quizDtoList = new ArrayList<>();
	Quiz quiz = new Quiz();
	QuizDto quizDto=new QuizDto();
	Question question;
	@BeforeEach
	public void setUp() {
		question=new Question("extension for java",Arrays.asList(".c",".java",".py"),"Medium","java","8");
		question.setAnswer("2");
		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("founder of java");
		questionDto.setDifficultyLevel("easy");
		questionDto.setTag("Java");
		questionDto.setOptions(Arrays.asList("james gosling", "andrew rubert"));
		questionDto.setId(1);
		questionDto.setAnswer("3");
		Quiz quiz = new Quiz("java",2);
		quiz.setId(1);
		quiz.setQuestion(Arrays.asList(question));
		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("java");
		quizDto.setQuestion(new ArrayList<>(Arrays.asList(question)));
		quizDto.setId(1);
		quizDto.setMarks(1);
		quizList = new ArrayList<>(Arrays.asList(quiz));
		quizDtoList = new ArrayList<>(Arrays.asList(quizDto));
	}
	@Test
	void createDuplicateQuizTest() throws QuizException {
		List<Integer> ids = Arrays.asList(1);
		Mockito.when(modelMapper.map(quizDto,Quiz.class)).thenReturn(quiz);
		Mockito.when(quizRepository.existsByTitle(quiz.getTitle())).thenReturn(true);
		assertThrows(QuizException.class, () -> quizLibraryServiceImpl.addQuiz(quizDto, ids));

	}
	
	
	
	@Test
	void createQuizTest() {
		List<Integer> questionIds = Arrays.asList(1);
		List<Question> questionList=Arrays.asList(question);
		Mockito.when(modelMapper.map(quizDto,Quiz.class)).thenReturn(quiz);
		Mockito.when(quizRepository.existsByTitle(quiz.getTitle())).thenReturn(false);
		Mockito.when(questionRepository.findAllById(questionIds)).thenReturn(questionList);
		Mockito.when(modelMapper.map(quiz,QuizDto.class)).thenReturn(quizDto);
		Mockito.when(quizRepository.save(quiz)).thenReturn(quiz);
		QuizDto savedQuizDto = quizLibraryServiceImpl.addQuiz(quizDto, questionIds);
		assertEquals(quizDto, savedQuizDto);
	}
	
	@Test
	void testViewAllQuiz() {
		quizList.add(quiz);
		Mockito.when(quizRepository.findAll()).thenReturn(quizList);
		List<Quiz> retrivedQuizList = quizLibraryServiceImpl.getAllQuizzes();
		Mockito.verify(quizRepository).findAll();
		assertEquals(quizList, retrivedQuizList);
	}
	
	@Test
	void testViewQuizByExistingId() {
		quiz.setId(2);
		Mockito.when(quizRepository.findById(2)).thenReturn(Optional.ofNullable(quiz));
		Mockito.when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
		QuizDto retrivedQuiz = quizLibraryServiceImpl.getQuizById(2);
		Mockito.verify(quizRepository).findById(2);
		assertEquals(quizDto, retrivedQuiz);
	}

	@Test
	void testViewQuizByUnExistingId() throws QuestionException {
		Mockito.when(quizRepository.findById(20)).thenReturn(Optional.empty());
		assertThrows(QuizException.class, () -> quizLibraryServiceImpl.getQuizById(20));
	}
	
	@Test
	void deleteQuizTest() {
		quiz.setId(1);
		quizDto.setId(1);
		Mockito.doNothing().when(quizRepository).deleteById(1);
		quizLibraryServiceImpl.deleteQuiz(quiz.getId());
		Mockito.verify(quizRepository).deleteById(1);
	}
	
	@Test
	void updateQuizwithUnKnownIdTest() throws QuestionException {
		List<Integer> ids = Arrays.asList(1);
		Mockito.when(modelMapper.map(quizDto,Quiz.class)).thenReturn(quiz);
		Mockito.when(quizRepository.findById(quiz.getId())).thenReturn(Optional.empty());
		assertThrows(QuizException.class, () -> quizLibraryServiceImpl.updateQuiz(quizDto, ids));

	}
	
	
	
	@Test
	void updateQuizTest() {
		List<Integer> questionIds = Arrays.asList(1);
		List<Question> questionList=Arrays.asList(question);
		Mockito.when(modelMapper.map(quizDto,Quiz.class)).thenReturn(quiz);
		Mockito.when(quizRepository.findById(quiz.getId())).thenReturn(Optional.of(quiz));
		Mockito.when(questionRepository.findAllById(questionIds)).thenReturn(questionList);
		Mockito.when(modelMapper.map(quiz,QuizDto.class)).thenReturn(quizDto);
		Mockito.when(quizRepository.save(quiz)).thenReturn(quiz);
		QuizDto savedQuizDto = quizLibraryServiceImpl.updateQuiz(quizDto, questionIds);
		assertEquals(quizDto, savedQuizDto);
	}


	
}
