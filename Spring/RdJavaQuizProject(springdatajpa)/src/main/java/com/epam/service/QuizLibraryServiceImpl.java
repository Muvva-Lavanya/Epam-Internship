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

@Service
public class QuizLibraryServiceImpl implements QuizLibraryService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public QuizDto addQuiz(QuizDto quizDto, List<Integer> questionIds) {
		Quiz quiz = modelMapper.map(quizDto, Quiz.class);
		if (quizRepository.findByTitle(quiz.getTitle()).isEmpty()) {
			List<Question> questionsList= (List<Question>)questionRepository.findAllById(questionIds);
			if(questionsList.size()!=questionIds.size())
			{
				throw new QuizException("Quiz cannot be created with some of the given question ids");
			}
			quiz.setQuestion(questionsList);
			return modelMapper.map(quizRepository.save(quiz),QuizDto.class);
		}
			throw new QuizException("Quiz title already exits. Please try with another title");
	} 

	@Override
	public QuizDto deleteQuiz(int id) throws QuizException {
		return quizRepository.findById(id).map(q -> {
			QuizDto quizDto=modelMapper.map(q,QuizDto.class);
			quizRepository.delete(q);
			return quizDto;
		}).orElseThrow(() -> new QuizException("Quiz with id doesnt exist. Please try with another id"));

	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto,List<Integer> ids) {
		Quiz quiz = modelMapper.map(quizDto, Quiz.class);
		if (quizRepository.findByTitle(quiz.getTitle()).isEmpty()) {
			List<Question> questionsList= (List<Question>)questionRepository.findAllById(ids);
			quiz.setQuestion(questionsList);
			if(questionsList.size()!=ids.size())
			{
				throw new QuizException("Quiz cannot be created with some of the given ids");
			}
			return modelMapper.map(quizRepository.save(quiz),QuizDto.class);
		} else { 
			throw new QuizException("Quiz title already exits. Please try with another title");
		}
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		return (List<Quiz>) quizRepository.findAll();
	}

	@Override
	public Quiz getQuizById(int id) throws QuizException {
		return quizRepository.findById(id).orElseThrow(() -> new QuizException("Quiz with id doesnt exist. Please try with another id"));
	}

}
