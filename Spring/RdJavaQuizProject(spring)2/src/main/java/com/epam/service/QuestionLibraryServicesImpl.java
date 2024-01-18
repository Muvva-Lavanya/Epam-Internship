package com.epam.service;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.customexception.QuestionCannotBeCreatedException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.database.QuestionDao;
import com.epam.entity.Question;
@Service
public class QuestionLibraryServicesImpl implements QuestionLibraryServices {
	@Autowired
	private QuestionDao questionDaoJpaImpl;
	
	@Override
	public Question createQuestion(Question question) throws QuestionCannotBeCreatedException {
		if (containQuestionTitle(question.getTitle()) == 0) {
			return questionDaoJpaImpl.save(question);
		} else {
			throw new QuestionCannotBeCreatedException();
		}
	}

	@Override
	public String deleteQuestion(Integer questionId) throws QuestionNotFoundException, NullPointerException {
		if (questionDaoJpaImpl.load(questionId) != null) {
			questionDaoJpaImpl.delete(questionId);
			return "Question deleted successfully";
		} else {
			throw new QuestionNotFoundException();
		}
	}

	@Override
	public List<Question> viewQuestion() {
		return questionDaoJpaImpl.loadAll();
	}

	@Override
	public String updateAnswer(int id, int correctOption) throws QuestionNotFoundException {
		String result = "";
		if (questionDaoJpaImpl.load(id) != null) {
			Question updateQuestion = questionDaoJpaImpl.load(id);
			if (correctOption >= 0 && correctOption < updateQuestion.getOptions().size()) {
				updateQuestion.setAnswer(updateQuestion.getOptions().get(correctOption));
				questionDaoJpaImpl.update(updateQuestion);
				result = "Question Answer updated successfully";
			} else {
				result = "Enter valid option index to update answer";
			}
		} else {
			throw new QuestionNotFoundException();
		}
		return result;
	}

	@Override
	public String updateTitle(int id, String title) throws QuestionNotFoundException {
		String result = "";
		if (questionDaoJpaImpl.load(id) != null) {
			Question updateQuestion = questionDaoJpaImpl.load(id);
			if (containQuestionTitle(title) == 0) {
				updateQuestion.setTitle(title);
				questionDaoJpaImpl.update(updateQuestion);
				result = "Question title updated successfully";
			} else {
				result = "Question already exists";
			}
		} else {
			throw new QuestionNotFoundException();
		}
		return result;
	}

	@Override
	public String updateDifficulty(int id, String difficulty) throws QuestionNotFoundException {
		String result = "";
		if (questionDaoJpaImpl.load(id) != null) {
			List<String> levels = Arrays.asList("Easy", "Medium", "Hard");
			if (levels.contains(difficulty)) {
				Question updateQuestion = questionDaoJpaImpl.load(id);
				updateQuestion.setDifficultyLevel(difficulty);
				questionDaoJpaImpl.update(updateQuestion);
				result = "Question difficultyLevel updated successfully";
			} else {
				result = "Choose one of the option for difficultyLevel- 0/1/2";
			}
		} else {
			throw new QuestionNotFoundException();
		}
		return result;
	}

	@Override
	public String updateTag(int id, String tag) throws QuestionNotFoundException {
		if (questionDaoJpaImpl.load(id) != null) {
			Question updateQuestion = questionDaoJpaImpl.load(id);
			updateQuestion.setTag(tag);
			questionDaoJpaImpl.update(updateQuestion);
			return "Question tag updated successfully";
		} else {
			throw new QuestionNotFoundException();
		}
	}

	@Override
	public String updateOptions(int id, int optionNo, String value) throws QuestionNotFoundException {
		String result = "";
		if (questionDaoJpaImpl.load(id) != null) {
			Question updateQuestion = questionDaoJpaImpl.load(id);
			if (optionNo >= 0 && optionNo <= updateQuestion.getOptions().size()) {
				updateQuestion.getOptions().set(optionNo - 1, value);
				questionDaoJpaImpl.update(updateQuestion);
				result = "Question option updated successfully";
			} else {
				result = "OptionIndexOutOfBounds";
			}
		} else {
			throw new QuestionNotFoundException();
		}
		return result;
	}

	private long containQuestionTitle(String title) {
		return questionDaoJpaImpl.loadAll().stream().filter(e->e.getTitle().equalsIgnoreCase(title)).count();

	}

	@Override
	public Question viewById(int id) throws QuestionNotFoundException {
		if (questionDaoJpaImpl.load(id) != null) {
			return questionDaoJpaImpl.load(id);
		} else {
			throw new QuestionNotFoundException();
		}
	}

}