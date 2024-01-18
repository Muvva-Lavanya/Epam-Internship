package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.AssociateDto;
import com.epam.dto.BatchDto;
import com.epam.exception.AssociateException;
import com.epam.model.Associate;
import com.epam.model.Batch;
import com.epam.repository.AssociateRepository;
import com.epam.repository.BatchRepository;

@ExtendWith(MockitoExtension.class)
 class AssociateServiceTest {
	
	@Mock
	ModelMapper modelMapper;
	@Mock
	AssociateRepository associateRepository;
	@Mock
	BatchRepository batchRepository;
	
	@InjectMocks
	AssociateServiceImpl associateServiceImpl;
	
	Associate associate;
	AssociateDto associateDto;
	Batch batch;
	BatchDto batchDto;
	
	@BeforeEach
	public void AsociateServiceTest()
	{
		batch=new Batch();
		batch.setId(1);
		batch.setName("rd-java-2023");
		batch.setPractice("java");
		batch.setStartDate(new Date());
		batch.setEndDate(new Date());
		
		batchDto=new BatchDto();
		batchDto.setEndDate(new Date());
		batchDto.setEndDate(new Date());
		batchDto.setId(1);
		batchDto.setName("rd-java-2023");
		batchDto.setPractice("java");
		
		associate =new Associate();
		associate.setId(1);
		associate.setName("lavanya");
		associate.setEmail("lavanya@gmail.com");
		associate.setCollege("vignan");
		associate.setGender("Male");
		associate.setStatus("Active");
		associate.setBatch(batch);
		
		associateDto=new AssociateDto();
		associateDto.setBatch(batch);
		associateDto.setCollege("vignan");
		associateDto.setEmail("lavanya@gmail.com");
		associateDto.setGender("Male");
		associateDto.setId(1);
		associateDto.setName("lavanya");
		associateDto.setStatus("Active");
		
	}
	
	@Test
	 void testCreateAssociate()
	{
		Mockito.when(modelMapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(batchRepository.save(batch)).thenReturn(batch);
		Mockito.when(associateRepository.save(associate)).thenReturn(associate);
		Mockito.when(modelMapper.map(associate,AssociateDto.class)).thenReturn(associateDto);
		assertEquals(associateDto,associateServiceImpl.createAssociate(associateDto));
		Mockito.verify(modelMapper).map(associateDto, Associate.class);
		Mockito.verify(batchRepository).save(batch);
		Mockito.verify(associateRepository).save(associate);
		Mockito.verify(modelMapper).map(associate,AssociateDto.class);
	}
	
	@Test
	 void testDeleteAssociate()
	{
		Mockito.doNothing().when(associateRepository).deleteById(1);
		associateServiceImpl.deleteAssociate(1);
		Mockito.verify(associateRepository).deleteById(1);
	}
	
	@Test
	 void testUpdateAssociate()
	{
		Mockito.when(associateRepository.findById(1)).thenReturn(Optional.of(associate));
		Mockito.when(modelMapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(associateRepository.save(associate)).thenReturn(associate);
		Mockito.when(modelMapper.map(associate,AssociateDto.class)).thenReturn(associateDto);
		assertEquals(associateDto,associateServiceImpl.updateAssociate(1, associateDto));
		Mockito.verify(associateRepository).findById(1);
		Mockito.verify(modelMapper).map(associateDto, Associate.class);
		Mockito.verify(associateRepository).save(associate);
		Mockito.verify(modelMapper).map(associate,AssociateDto.class);
	}
	
	@Test
	 void testUpdateAssociateFails()
	{
		Mockito.when(associateRepository.findById(100)).thenReturn(Optional.empty());
		assertThrows(AssociateException.class,()->associateServiceImpl.updateAssociate(100, associateDto));
		Mockito.verify(associateRepository).findById(100);
	}
	@Test
	 void testGetAssociatesByGender()
	{
		Associate associateMale=new Associate();
		associateMale.setBatch(associate.getBatch());
		associateMale.setCollege(associate.getCollege());
		associateMale.setEmail(associate.getEmail());
		associateMale.setGender(associate.getGender());
		associateMale.setId(associate.getId());
		associateMale.setName(associate.getName());
		associateMale.setStatus(associate.getStatus());
		Mockito.when(associateRepository.findAllByGender("Male")).thenReturn(List.of(associate));
		Mockito.when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		assertEquals(List.of(associateDto),associateServiceImpl.getAllAssociatesByGender("Male"));
		Mockito.verify(associateRepository).findAllByGender("Male");
		Mockito.verify(modelMapper).map(associate,AssociateDto.class);
		
	}
	
	

}
