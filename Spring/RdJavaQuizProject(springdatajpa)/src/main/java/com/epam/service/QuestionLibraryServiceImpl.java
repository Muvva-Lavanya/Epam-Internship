package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.repository.QuestionRepository;

@Service
public class QuestionLibraryServiceImpl implements QuestionLibraryService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public QuestionDto createQuestion(QuestionDto questionDto) throws QuestionException {
		Question question = modelMapper.map(questionDto, Question.class);
		if (questionRepository.findByTitle(question.getTitle()).isEmpty()) {
			return modelMapper.map(questionRepository.save(question), QuestionDto.class);
		} else {
			throw new QuestionException("Duplicate Question. Please try with a different question title");
		}
	}

	@Override
	public QuestionDto deleteQuestion(Integer questionId) throws QuestionException, DataIntegrityViolationException {
		return questionRepository.findById(questionId).map(q -> {
			QuestionDto questionDto = modelMapper.map(q, QuestionDto.class);
			questionRepository.delete(q);
			return questionDto;
		}).orElseThrow(() -> new QuestionException("Question id doesnt exist.Please try with different id"));
	}

	@Override
	public List<Question> getQuestions() {
		return (List<Question>) questionRepository.findAll();
	}

	@Override
	public QuestionDto getQuestionById(int id) throws QuestionException {
		return questionRepository.findById(id).map(q -> modelMapper.map(q, QuestionDto.class))
				.orElseThrow(() -> new QuestionException("Question id doesnt exist. Please try with different id"));
	}

	public QuestionDto updateQuestion(QuestionDto questionDto) throws QuestionException {
		Question question = modelMapper.map(questionDto, Question.class);
		return questionRepository.findById(question.getId())
				.map(q -> modelMapper.map(questionRepository.save(question), QuestionDto.class))
				.orElseThrow(() -> new QuestionException("Question id doesnt exist.Please try with different id"));

	}

}