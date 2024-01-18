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
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.repository.QuestionRepository;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryServiceTest {

	@Mock
	private QuestionRepository questionRepository;
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private QuestionLibraryServiceImpl questionLibraryServiceImpl;
	Question question;
	QuestionDto questionDto;
	List<Question> questionList;
	List<QuestionDto> questionDtoList;

	@BeforeEach
	public void setUp() {
		questionDto=new QuestionDto(1,"extension for java",Arrays.asList(".c",".java",".py"),"Medium","java",".java");
		question=new Question("extension for java",Arrays.asList(".c",".java",".py"),"Medium","java",".java");
		questionList = new ArrayList<>(Arrays.asList(question));
		questionDtoList = new ArrayList<>(Arrays.asList(questionDto));
	}

	@Test
	void createQuestionTest() throws QuestionException {
		question.setId(1);
		questionDto.setId(1);
		Mockito.when(modelMapper.map(questionDto,Question.class)).thenReturn(question);
		Mockito.when(questionRepository.existsByTitle("extension for java")).thenReturn(false);
		Mockito.when(modelMapper.map(question,QuestionDto.class)).thenReturn(questionDto);
		Mockito.when(questionRepository.save(question)).thenReturn(question);
		QuestionDto savedQuestion = questionLibraryServiceImpl.createQuestion(questionDto);
		Mockito.verify(modelMapper).map(questionDto, Question.class);
		Mockito.verify(questionRepository).existsByTitle("extension for java");
		Mockito.verify(questionRepository).save(question);
		assertEquals(questionDto, savedQuestion);
	}

	@Test
	void createDuplicationQuestionTest() throws QuestionException{
		QuestionDto questionDto = questionDtoList.get(0);
		Question question = questionList.get(0);
		Mockito.when(modelMapper.map(questionDto,Question.class)).thenReturn(question);
		Mockito.when(questionRepository.existsByTitle("extension for java")).thenReturn(true);
		assertThrows(QuestionException.class, () -> questionLibraryServiceImpl.createQuestion(questionDto));
		Mockito.verify(modelMapper).map(questionDto, Question.class);
		Mockito.verify(questionRepository).existsByTitle("extension for java");
	}
 
	@Test
	void viewQuestionByIdTest() {
		question.setId(1);
		Mockito.when(questionRepository.findById(question.getId())).thenReturn(Optional.ofNullable(question));
		Mockito.when(modelMapper.map(question,QuestionDto.class)).thenReturn(questionDto);
		QuestionDto retrievedQuestion = questionLibraryServiceImpl.getQuestionById(question.getId());
		Mockito.verify(questionRepository).findById(question.getId());
		assertEquals(questionDto, retrievedQuestion);
	}

	@Test
	void viewQuestionWithoutIdPresentTest() {
		Mockito.when(questionRepository.findById(987)).thenReturn(Optional.empty());
		assertThrows(QuestionException.class, () -> questionLibraryServiceImpl.getQuestionById(987));
		Mockito.verify(questionRepository).findById(987);
	}

	@Test
	void viewAllQuestions() {
		Mockito.when(questionRepository.findAll()).thenReturn(questionList);
		List<Question> retrievedQuestionsList = questionLibraryServiceImpl.getQuestions();
		Mockito.verify(questionRepository).findAll();
		assertEquals(questionList, retrievedQuestionsList);
		
	}
	@Test
	void updateQuestion()
	{
		question.setId(1);
		questionDto.setId(1);
		Mockito.when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		Mockito.when(questionRepository.findById(1)).thenReturn(Optional.of(question));
		Mockito.when(questionRepository.save(question)).thenReturn(question);
		Mockito.when(modelMapper.map(question,QuestionDto.class)).thenReturn(questionDto);
		QuestionDto updatedQuestion=questionLibraryServiceImpl.updateQuestion(questionDto);
		assertEquals(questionDto,updatedQuestion);
	}
	
	@Test
	void updateUnExistingQuestion()
	{
		question.setId(1);
		questionDto.setId(1);
		Mockito.when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		Mockito.when(questionRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(QuestionException.class,()->questionLibraryServiceImpl.updateQuestion(questionDto));
	}
	
	@Test
	void updateWrongAnswerForQuestion()
	{
		question=new Question("extension for java",Arrays.asList(".c",".java",".py"),"Medium","java",".8");
		questionDto=new QuestionDto(1,"extension for java",Arrays.asList(".c",".java",".py"),"Medium","java",".8");
		question.setId(1);
		questionDto.setId(1);
		Mockito.when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		Mockito.when(questionRepository.findById(1)).thenReturn(Optional.of(question));
		assertThrows(QuestionException.class,()->questionLibraryServiceImpl.updateQuestion(questionDto));
	}

	
	@Test
	void deleteQuestionTest() {
		question.setId(1);
		Mockito.doNothing().when(questionRepository).deleteById(1);
		questionLibraryServiceImpl.deleteQuestion(1);
		Mockito.verify(questionRepository).deleteById(1);
	}

}
