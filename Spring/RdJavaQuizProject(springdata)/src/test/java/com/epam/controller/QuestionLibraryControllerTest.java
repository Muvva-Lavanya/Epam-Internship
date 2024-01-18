package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.RedundantQuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryControllerTest {
	@Mock
	private QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@InjectMocks
	private QuestionLibraryController questionLibraryController;
	private MockMvc mockMvc;
	Question question;
	QuestionDto questionDto;
	List<Question> questionList;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(questionLibraryController).build();
		questionDto = new QuestionDto("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java", "8");
		question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java", "8");
	}

	@Test
	void testDeleteExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.deleteQuestion(1)).thenReturn(question);
		mockMvc.perform(get("/deleteQuestion").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question with id: 1 deleted successfully")).andReturn();
	}

	@Test
	void testDeleteUnExistingQuestion() throws Exception {
		question.setId(100);
		Mockito.when(questionLibraryServiceImpl.deleteQuestion(100)).thenThrow(new QuestionNotFoundException());
		mockMvc.perform(get("/deleteQuestion").param("id", "100")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}

	@Test
	void testUpdateTitleForExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateTitle(1, "who invented java")).thenReturn(question);
		mockMvc.perform(post("/updateTitle").param("id", "1").param("title", "who invented java"))
				.andExpect(status().isOk()).andExpect(view().name("updationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question title with id: 1 updated successfully")).andReturn();
	}

	@Test
	void testUpdateTitleForUnExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateTitle(1, "who invented java"))
				.thenThrow(new QuestionNotFoundException());
		mockMvc.perform(post("/updateTitle").param("id", "1").param("title", "who invented java"))
				.andExpect(status().isOk()).andExpect(view().name("updationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}

	@Test
	void testUpdateOptionsForExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateOptions(100, 1, "hello")).thenReturn(question);
		mockMvc.perform(post("/updateOption").param("id", "100").param("index", "1").param("value", "hello"))
				.andExpect(status().isOk()).andExpect(view().name("updationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question option with id: 1 updated successfully")).andReturn();
	}

	@Test
	void testUpdateOptionsForUnExistingQuestion() throws Exception {
		question.setId(1); 
		Mockito.when(questionLibraryServiceImpl.updateOptions(100, 1, "hello"))
				.thenThrow(new QuestionNotFoundException());
		mockMvc.perform(post("/updateOption").param("id", "100").param("index", "1").param("value", "hello"))
				.andExpect(status().isOk()).andExpect(view().name("updationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}

	@Test
	void testUpdateTagForExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateTag(1, "java")).thenReturn(question);
		mockMvc.perform(post("/updateTag").param("id", "1").param("tag", "java")).andExpect(status().isOk())
				.andExpect(view().name("updationStatus.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question tag with id: 1 updated successfully")).andReturn();
	}

	@Test
	void testUpdateTagForUnExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateTag(100, "java")).thenThrow(new QuestionNotFoundException());
		mockMvc.perform(post("/updateTag").param("id", "100").param("tag", "java")).andExpect(status().isOk())
				.andExpect(view().name("updationStatus.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}

	@Test
	void testUpdateDifficultyLevelForExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateDifficulty(1, "easy")).thenReturn(question);
		mockMvc.perform(post("/updateDifficultyLevel").param("id", "1").param("difficultyLevel", "easy"))
				.andExpect(status().isOk()).andExpect(view().name("updationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question difficultyLevel with id: 1 updated successfully"))
				.andReturn();
	}

	@Test
	void testUpdateDifficultyLevelForUnExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateDifficulty(100, "easy"))
				.thenThrow(new QuestionNotFoundException());
		mockMvc.perform(post("/updateDifficultyLevel").param("id", "100").param("difficultyLevel", "easy"))
				.andExpect(status().isOk()).andExpect(view().name("updationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}

	@Test
	void testUpdateAnswerForExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateAnswer(1, "abc")).thenReturn(question);
		mockMvc.perform(post("/updateAnswer").param("id", "1").param("answer", "abc")).andExpect(status().isOk())
				.andExpect(view().name("updationStatus.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question answer with id: 1 updated successfully")).andReturn();
	}

	@Test
	void testUpdateAnswerForUnExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.updateAnswer(100, "abc")).thenThrow(new QuestionNotFoundException());
		mockMvc.perform(post("/updateAnswer").param("id", "100").param("answer", "abc")).andExpect(status().isOk())
				.andExpect(view().name("updationStatus.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}

	@Test
	void testViewQuestions() throws Exception
	{
		questionList=new ArrayList<>();
		questionList.add(question);
		Mockito.when(questionLibraryServiceImpl.viewQuestion()).thenReturn(questionList);
		mockMvc.perform(get("/viewquestions")).andExpect(status().isOk())
				.andExpect(view().name("viewQuestions.jsp")).andExpect(model().attributeExists("questionsList"))
				.andExpect(model().attribute("questionsList",questionList)).andReturn();
	}
	
	@Test
	void testViewEmptyQuestions() throws Exception
	{
		questionList=List.of();
		Mockito.when(questionLibraryServiceImpl.viewQuestion()).thenReturn(questionList);
		mockMvc.perform(get("/viewquestions")).andExpect(status().isOk())
				.andExpect(view().name("viewQuestions.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","There are No Questions to display")).andReturn();
	}
	
	@Test
	void testCreateQuestion() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.createQuestion(any())).thenReturn(question);
		mockMvc.perform(post("/createQuestion").param("questionDto","questionDto")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","Question with id: 0 created successfully")).andReturn();
	}
	
	@Test
	void testCreateExistingQuestion() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.createQuestion(any())).thenThrow(new RedundantQuestionException());
		mockMvc.perform(post("/createQuestion").param("questionDto","questionDto")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","Question already exists")).andReturn();
	
	}

}
