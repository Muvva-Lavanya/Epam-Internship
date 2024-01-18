package com.epam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	Logger logger = LogManager.getLogger(QuestionLibraryServiceImpl.class);

	@Override
	public QuestionDto createQuestion(QuestionDto questionDto) throws QuestionException {
		Question question = modelMapper.map(questionDto, Question.class);
		logger.info("Question:create");
		if (!questionRepository.existsByTitle(questionDto.getTitle())) {
			Question createdQuestion = questionRepository.save(question);
			logger.info("Question with id : {} created successfully",createdQuestion.getId());
			return modelMapper.map(createdQuestion, QuestionDto.class);
		} else {
			throw new QuestionException("Duplicate Question. Please try with a different question title");
		}
	}

	@Override
	public void deleteQuestion(Integer questionId) {
		logger.info("Question:delete");
		questionRepository.deleteById(questionId);
	}

	@Override
	public List<Question> getQuestions() {
		logger.info("Question:View Questions");
		return questionRepository.findAll();
	}

	@Override
	public QuestionDto getQuestionById(int id) throws QuestionException {
		logger.info("Question:View Question by id");
		return questionRepository.findById(id).map(q -> {
			logger.info("Question with id : {} retrived successfully",id);
			return modelMapper.map(q, QuestionDto.class);
		}).orElseThrow(() -> new QuestionException("Question id doesnt exist.Please try with different id"));
	} 

	public QuestionDto updateQuestion(QuestionDto questionDto) throws QuestionException {
		logger.info("Question:update");
		Question question = modelMapper.map(questionDto, Question.class);
		return questionRepository.findById(question.getId()).map(q -> {
			Question updatedQuestion = questionRepository.save(question);
			logger.info("Question with id : {} updated successfully",questionDto.getId());
			return modelMapper.map(updatedQuestion, QuestionDto.class);
		}).orElseThrow(() ->new QuestionException("Question id doesnt exist.Please try with different id"));

	}

}