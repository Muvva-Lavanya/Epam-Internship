package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexception.QuestionCannotBeCreatedException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.database.QuestionDao;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServicesImpl;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryServiceTest {
	@Mock
	private QuestionDao questionDaoJpaImpl;
	@InjectMocks
	private QuestionLibraryServicesImpl questionLibraryServicesImpl;
	Question question1, question2;

	@BeforeEach
	public void setUp() {
		question1 = new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "Test", "1");
		question2 = new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "Test", "1");
	}
 
	@Test
	void testCreateQuestion() throws QuestionCannotBeCreatedException {
		List<Question> questions=Arrays.asList(question1,question2);
		Question question3=new Question("extension for java",Arrays.asList(".c",".java",".py"),"Medium","java","8");
		Mockito.when(questionDaoJpaImpl.loadAll()).thenReturn(questions);
		Mockito.when(questionDaoJpaImpl.save(question3)).thenReturn(question3);
		Question savedQuestion = questionLibraryServicesImpl.createQuestion(question3);
		Mockito.verify(questionDaoJpaImpl).loadAll();
		Mockito.verify(questionDaoJpaImpl).save(question3);
		assertEquals(question3, savedQuestion);
		
	}
	
	@Test
	void testCreateExistingQuestion() throws QuestionCannotBeCreatedException {
		List<Question> questions=Arrays.asList(question1,question2);
		Mockito.when(questionDaoJpaImpl.loadAll()).thenReturn(questions);
		assertThrows(QuestionCannotBeCreatedException.class,()->questionLibraryServicesImpl.createQuestion(question2));
		Mockito.verify(questionDaoJpaImpl).loadAll();
		
		
	}
 
	

	@Test
	void testdeleteExistingQuestion() throws QuestionNotFoundException {
		question1.setId(1);
		Mockito.when(questionDaoJpaImpl.load(1)).thenReturn(question1);
		Mockito.doNothing().when(questionDaoJpaImpl).delete(1);
		String result = questionLibraryServicesImpl.deleteQuestion(1);
		Mockito.verify(questionDaoJpaImpl).load(1);
		Mockito.verify(questionDaoJpaImpl).delete(1);
		assertEquals("Question deleted successfully", result);
	}

	@Test
	void testdeleteUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.deleteQuestion(1000));
		Mockito.verify(questionDaoJpaImpl).load(1000);
		
	}

	@Test 
	void testViewAllQuestions() {
		List<Question> questionsList = Arrays.asList(question1, question2);
		Mockito.when(questionDaoJpaImpl.loadAll()).thenReturn(questionsList);
		List<Question> retrivedQuestions = questionLibraryServicesImpl.viewQuestion();
		Mockito.verify(questionDaoJpaImpl).loadAll();
		System.out.println(questionsList+" "+retrivedQuestions);
		assertEquals(questionsList, retrivedQuestions);
	}

	@Test
	void viewExistingQuestionById() throws QuestionNotFoundException {
		question1.setId(2);
		Mockito.when(questionDaoJpaImpl.load(2)).thenReturn(question1);
		Question retrivedQuestion = questionLibraryServicesImpl.viewById(2);
		Mockito.verify(questionDaoJpaImpl, times(2)).load(2);
		assertEquals(question1, retrivedQuestion);
	}

	@Test
	void viewUnExistingQuestionById() {
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.viewById(1000));
		Mockito.verify(questionDaoJpaImpl).load(1000);
		
	} 

	@Test
	void updateTagForExistingQuestion() {
		question2.setId(2);
		Mockito.when(questionDaoJpaImpl.load(2)).thenReturn(question2);
		question2.setTag("java");
		Mockito.when(questionDaoJpaImpl.update(question2)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateTag(2, "java");
		Mockito.verify(questionDaoJpaImpl, times(2)).load(2);
		Mockito.verify(questionDaoJpaImpl).update(question2);
		assertEquals("Question tag updated successfully", message);
	}

	@Test
	void updateTagForUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateTag(1000,"hello world"));
		Mockito.verify(questionDaoJpaImpl).load(1000);
		
	}

	@Test
	void updateDifficultyLevelForExistingQueztion() {

		question2.setId(2);
		Mockito.when(questionDaoJpaImpl.load(2)).thenReturn(question2);
		question2.setDifficultyLevel("Easy");
		Mockito.when(questionDaoJpaImpl.update(question2)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateDifficulty(2, "Easy");
		Mockito.verify(questionDaoJpaImpl, times(2)).load(2);
		Mockito.verify(questionDaoJpaImpl).update(question2);
		assertEquals("Question difficultyLevel updated successfully", message);
	}

	@Test
	void updateWrongDifficultyLevelForExistingQueztion() {

		question2.setId(3);
		Mockito.when(questionDaoJpaImpl.load(3)).thenReturn(question2);
		question2.setDifficultyLevel("Easy1");
		String message = questionLibraryServicesImpl.updateDifficulty(3, "Easy1");
		Mockito.verify(questionDaoJpaImpl).load(3);
		assertEquals("Choose one of the option for difficultyLevel- 0/1/2", message);
	}

	@Test
	void updateDifficultyLevelForUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateDifficulty(1000,"Easy"));
		Mockito.verify(questionDaoJpaImpl).load(1000);
		
	}

	@Test
	void updateOptionsForExistingQuestion() {
		question2.setId(3);
		Mockito.when(questionDaoJpaImpl.load(3)).thenReturn(question2);
		question2.getOptions().set(1, "1");
		String message = questionLibraryServicesImpl.updateOptions(3, 1, "1");
		Mockito.verify(questionDaoJpaImpl, times(2)).load(3);
		assertEquals("Question option updated successfully", message);
	}

	@Test
	void updateWrongOptions1ForExistingQuestion() {
		question2.setId(4);
		Mockito.when(questionDaoJpaImpl.load(4)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateOptions(4, 5, "1");
		Mockito.verify(questionDaoJpaImpl, times(2)).load(4);
		assertEquals("OptionIndexOutOfBounds", message);
	}

	@Test
	void updateWrongOptions2ForExistingQuestion() {
		question2.setId(4);
		Mockito.when(questionDaoJpaImpl.load(4)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateOptions(4, -1, "1");
		Mockito.verify(questionDaoJpaImpl, times(2)).load(4);
		assertEquals("OptionIndexOutOfBounds", message);
	}

	@Test
	void updateOptionForUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateOptions(1000, 1,"1"));
		Mockito.verify(questionDaoJpaImpl).load(1000);
		
	}

	@Test
	void updateAnswerForExistingQuestion() {
		question2.setId(4);
		Mockito.when(questionDaoJpaImpl.load(4)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateAnswer(4, 0);
		question2.setAnswer("1");
		Mockito.verify(questionDaoJpaImpl, times(2)).load(4);
		assertEquals("Question Answer updated successfully", message);

	}

	@Test
	void updateWrongAnswer1ForExistingQuestion() {
		question2.setId(5);
		Mockito.when(questionDaoJpaImpl.load(5)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateAnswer(5, 5);
		Mockito.verify(questionDaoJpaImpl, times(2)).load(5);
		assertEquals("Enter valid option index to update answer", message);

	}

	@Test
	void updateWrongAnswer2ForExistingQuestion() {
		question2.setId(5);
		Mockito.when(questionDaoJpaImpl.load(5)).thenReturn(question2);
		String message = questionLibraryServicesImpl.updateAnswer(5, -1);
		Mockito.verify(questionDaoJpaImpl, times(2)).load(5);
		assertEquals("Enter valid option index to update answer", message);

	}

	@Test
	void updateAnswerForUnExistingQuestion() {
		question2.setId(1000);
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateAnswer(1000,10));
		Mockito.verify(questionDaoJpaImpl).load(1000);
	}
	@Test
	void updateTitleForExistingQuestion() {
		question2.setId(5);
		Mockito.when(questionDaoJpaImpl.load(5)).thenReturn(question2);
		question2.setTitle("hello world");
		Mockito.when(questionDaoJpaImpl.update(question2)).thenReturn(question2);
		String message=questionLibraryServicesImpl.updateTitle(5,"hello world");
		Mockito.verify(questionDaoJpaImpl,times(2)).load(5);
		Mockito.verify(questionDaoJpaImpl).update(question2);
		assertEquals("Question title updated successfully", message);
	}
	@Test
	void updateExistingTitleForExistingQuestion() {
		List<Question> questions=Arrays.asList(question1,question2);
		Mockito.when(questionDaoJpaImpl.loadAll()).thenReturn(questions);
		question2.setId(5);
		Mockito.when(questionDaoJpaImpl.load(5)).thenReturn(question2);
		String message=questionLibraryServicesImpl.updateTitle(5,"National bird of india");
		Mockito.verify(questionDaoJpaImpl,times(2)).load(5);
		assertEquals("Question already exists", message);
	}
	@Test
	void updateTitleForUnExistingQuestion() {
		Mockito.when(questionDaoJpaImpl.load(1000)).thenReturn(null);
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateTitle(1000,"hello world"));
		Mockito.verify(questionDaoJpaImpl).load(1000);
		
	} 
}
