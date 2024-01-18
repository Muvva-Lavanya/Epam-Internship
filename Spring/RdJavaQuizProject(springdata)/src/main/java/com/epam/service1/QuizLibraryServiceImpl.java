package com.epam.service1;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.QuizNotFoundException;
import com.epam.database.QuestionDao;
import com.epam.database.QuizDao;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;

@Service
public class QuizLibraryServiceImpl implements QuizLibraryService {
	@Autowired
	@Qualifier(value = "quizDaoJpaImpl")
	private QuizDao quizDaoJpaImpl;
	@Autowired
	@Qualifier(value = "questionDaoJpaImpl")
	private QuestionDao questionDaoJpaImpl;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public Quiz addQuiz(QuizDto quizDto, List<Integer> questionIds) {
		Quiz quiz=modelMapper.map(quizDto,Quiz.class);
		List<Question> questionsList = questionIds.stream().filter(id -> questionRepository.findById(id).isPresent())
				.map(id -> questionRepository.findById(id).get()).toList();
		quiz.setQuestion(questionsList);
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz deleteQuiz(int id) throws QuizNotFoundException {
		Optional<Quiz> getQuiz=quizRepository.findById(id);
		return getQuiz.map(q->{
			quizRepository.delete(q);
			return q;
		}).orElseThrow(QuizNotFoundException::new);

	}

	@Override
	public Quiz updateQuiz(int id, String newTitle) throws QuizNotFoundException {
		return quizDaoJpaImpl.viewQuizById(id).map(updatequiz -> {
			updatequiz.setTitle(newTitle);
			return quizDaoJpaImpl.updateQuiz(updatequiz);
		}).orElseThrow(QuizNotFoundException::new);
	}

	@Override
	public List<Quiz> viewQuiz() {
		return (List<Quiz>) quizRepository.findAll();
	}

	@Override
	public Quiz viewQuizById(int id) throws QuizNotFoundException {
		return quizRepository.findById(id).map(q -> q).orElseThrow(QuizNotFoundException::new);
	}

}
