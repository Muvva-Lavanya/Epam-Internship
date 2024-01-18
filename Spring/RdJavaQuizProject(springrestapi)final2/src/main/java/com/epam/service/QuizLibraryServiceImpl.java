package com.epam.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuizException;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizLibraryServiceImpl implements QuizLibraryService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public QuizDto addQuiz(QuizDto quizDto, List<Integer> questionIds) throws QuizException{
		log.info("Quiz:create");
		Quiz quiz = modelMapper.map(quizDto, Quiz.class);
		if (!quizRepository.existsByTitle(quizDto.getTitle())) {
			List<Question> questionsList =  questionRepository.findAllById(questionIds);
			quiz.setQuestion(questionsList);
			Quiz createdQuiz = quizRepository.save(quiz);
			log.info("Quiz with id : {} created successfully", createdQuiz.getId());
			return modelMapper.map(createdQuiz, QuizDto.class);
		} else {
			throw new QuizException("Quiz title already exits. Please try with another title");
		}
	}

	@Override
	public void deleteQuiz(int id) throws QuizException {
		log.info("Quiz:delete");
		quizRepository.deleteById(id);

	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto, List<Integer> ids) throws QuizException{
		log.info("Quiz:update");
		Quiz quiz = modelMapper.map(quizDto, Quiz.class);
		return quizRepository.findById(quiz.getId()).map(q -> {
			List<Question> questionsList = questionRepository.findAllById(ids);
			quiz.setQuestion(questionsList);
			Quiz updatedQuiz = quizRepository.save(quiz);
			log.info("Quiz with id : {} updated successfully",quizDto.getId());
			return modelMapper.map(updatedQuiz, QuizDto.class);
		}).orElseThrow(() ->new QuizException("Quiz with id doesnt exist.Please try with different id"));
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		log.info("Quiz:View Quizzes");
		return quizRepository.findAll();
	}

	@Override
	public QuizDto getQuizById(int id) throws QuizException {
		log.info("Quiz:View Quiz by id");
		return quizRepository.findById(id).map(q -> {
			log.info("Quiz with id : {} retrived successfully",id);
			return modelMapper.map(q, QuizDto.class);
		}).orElseThrow(() -> new QuizException("Quiz with id doesnt exist.Please try with different id"));
	}

}
