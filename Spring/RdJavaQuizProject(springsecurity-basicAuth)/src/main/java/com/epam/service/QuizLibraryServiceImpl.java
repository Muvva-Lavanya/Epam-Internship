package com.epam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuizException;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;

@Service
public class QuizLibraryServiceImpl implements QuizLibraryService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	Logger logger = LogManager.getLogger(QuizLibraryServiceImpl.class);

	@Override
	public QuizDto addQuiz(QuizDto quizDto, List<Integer> questionIds) throws QuizException{
		logger.info("Quiz:create");
		Quiz quiz = modelMapper.map(quizDto, Quiz.class);
		if (!quizRepository.existsByTitle(quizDto.getTitle())) {
			List<Question> questionsList =  questionRepository.findAllById(questionIds);
			quiz.setQuestion(questionsList);
			Quiz createdQuiz = quizRepository.save(quiz);
			logger.info("Quiz with id : {} created successfully", createdQuiz.getId());
			return modelMapper.map(createdQuiz, QuizDto.class);
		} else {
			throw new QuizException("Quiz title already exits. Please try with another title");
		}
	}

	@Override
	public void deleteQuiz(int id) throws QuizException {
		logger.info("Quiz:delete");
		quizRepository.deleteById(id);

	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto, List<Integer> ids) throws QuizException{
		logger.info("Quiz:update");
		Quiz quiz = modelMapper.map(quizDto, Quiz.class);
		return quizRepository.findById(quiz.getId()).map(q -> {
			List<Question> questionsList = questionRepository.findAllById(ids);
			quiz.setQuestion(questionsList);
			Quiz updatedQuiz = quizRepository.save(quiz);
			logger.info("Quiz with id : {} updated successfully",quizDto.getId());
			return modelMapper.map(updatedQuiz, QuizDto.class);
		}).orElseThrow(() ->new QuizException("Quiz with id doesnt exist.Please try with different id"));
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		logger.info("Quiz:View Quizzes");
		return quizRepository.findAll();
	}

	@Override
	public QuizDto getQuizById(int id) throws QuizException {
		logger.info("Quiz:View Quiz by id");
		return quizRepository.findById(id).map(q -> {
			logger.info("Quiz with id : {} retrived successfully",id);
			return modelMapper.map(q, QuizDto.class);
		}).orElseThrow(() -> new QuizException("Quiz with id doesnt exist.Please try with different id"));
	}

}
