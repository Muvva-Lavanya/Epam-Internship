package com.epam.service1;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.RedundantQuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.repository.QuestionRepository;

import jakarta.persistence.RollbackException;

@Service
public class QuestionLibraryServiceImpl implements QuestionLibraryService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public Question createQuestion(QuestionDto questionDto) {
		Question question=modelMapper.map(questionDto,Question.class);
		return questionRepository.save(question);
	}

	@Override
	public Question deleteQuestion(Integer id) throws QuestionNotFoundException, RollbackException {
		Optional<Question> getQuestion=questionRepository.findById(id);
		return getQuestion.map(q->{
			questionRepository.delete(q);
			return q;
		}).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public List<Question> viewQuestion() {
		return (List<Question>) questionRepository.findAll();
	}

	@Override
	public Question viewById(int id) throws QuestionNotFoundException {
		Optional<Question> getQuestion=questionRepository.findById(id);
		return getQuestion.map(q->q).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public Question updateQuestion(QuestionDto questionDto) {
		Question question=modelMapper.map(questionDto,Question.class);
		return questionRepository.save(question);
	}

	
}