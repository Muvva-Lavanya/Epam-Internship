package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.AssociateDto;
import com.epam.exception.AssociateException;
import com.epam.model.Associate;
import com.epam.repository.AssociateRepository;
import com.epam.repository.BatchRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociateServiceImpl implements AssociateService{
	
	@Autowired
	AssociateRepository associateRepository;
	
	@Autowired
	BatchRepository batchRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public AssociateDto createAssociate(AssociateDto associateDto) {
		log.info("Entered into createAssociateMethod:{}",associateDto);
		Associate associate=modelMapper.map(associateDto,Associate.class);
		batchRepository.save(associate.getBatch()); 
		Associate savedAssociate=associateRepository.save(associate);
		log.info("Associate :{} created successfully",savedAssociate);
		return modelMapper.map(savedAssociate,AssociateDto.class);
	}

	@Override
	public AssociateDto updateAssociate(int id, AssociateDto associateDto) {
		log.info("Entered into updateAssociateMethod: {}",associateDto);
		return associateRepository.findById(id).map(associate->{
			Associate asociate=modelMapper.map(associateDto,Associate.class);
			asociate.setId(id);
			Associate updatedAssociate=associateRepository.save(asociate);
			log.info("Associate :{} updated successfully",updatedAssociate);
			return modelMapper.map(updatedAssociate,AssociateDto.class);
		}).orElseThrow(()->new AssociateException("Id doesnt exist. Please try with different id"));
	}

	@Override
	public void deleteAssociate(int id) {
		log.info("Entered into deleteAssociateMethod");
		associateRepository.deleteById(id);
	}

	@Override
	public List<AssociateDto> getAllAssociatesByGender(String gender) {
		log.info("Entered into getAllAssociatesByGenderMethod");
		return associateRepository.findAllByGender(gender).stream().map(associate->modelMapper.map(associate,AssociateDto.class)).toList();
	}


}
