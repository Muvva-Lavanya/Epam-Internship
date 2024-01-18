package com.epam.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.AssociateDto;
import com.epam.dto.BatchDto;
import com.epam.model.Batch;
import com.epam.service.AssociateServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AssociateRestController.class)
class AssociateRestControllerTest {
	
	@MockBean
	AssociateServiceImpl associateServiceImpl;
	
	@Autowired
	MockMvc mockMvc;
	
	AssociateDto associateDto;
	BatchDto batchDto;
	Batch batch;
	
	@BeforeEach
	public void AssociateRestController()
	{
		batchDto=new BatchDto();
		batchDto.setEndDate(new Date());
		batchDto.setEndDate(new Date());
		batchDto.setId(1);
		batchDto.setName("rd-java-2023");
		batchDto.setPractice("java");
		
		batch=new Batch();
		batch.setId(1);
		batch.setName("rd-java-2023");
		batch.setPractice("java");
		batch.setStartDate(new Date());
		batch.setEndDate(new Date());
		
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
	 void testCreateAssociate() throws JsonProcessingException, Exception
	{
		Mockito.when(associateServiceImpl.createAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isCreated()).andReturn();
	}
	
	@Test
	 void testCreateAssociateWithMethodArgumentInvalidException() throws JsonProcessingException,Exception
	{
		AssociateDto associateDto=new AssociateDto();
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isBadRequest()).andReturn();
		
	}
	
	@Test
	 void testCreateAssociateWithHttpMessageNotReadableException() throws JsonProcessingException, Exception
	{
		Mockito.when(associateServiceImpl.createAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString("{name:"))).andExpect(status().isBadRequest()).andReturn();
	}
	
	@Test
	 void testDeleteAssociate() throws JsonProcessingException,Exception
	{
		Mockito.doNothing().when(associateServiceImpl).deleteAssociate(1);
		mockMvc.perform(delete("/associates/{id}",1)).andExpect(status().isNoContent()).andReturn();
	}
	
	@Test
	 void testUpdateAssociate() throws JsonProcessingException,Exception
	{
		Mockito.when(associateServiceImpl.updateAssociate(1, associateDto)).thenReturn(associateDto);
		mockMvc.perform(put("/associates/{id}",1).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isOk()).andReturn();
	}
	
	
	
	@Test
	 void testGetAssociatesByGender() throws JsonProcessingException,Exception
	{
		Mockito.when(associateServiceImpl.getAllAssociatesByGender("Male")).thenReturn(List.of(associateDto));
		mockMvc.perform(get("/associates/{gender}","Male")).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	 void testMethodArgumentMisMatchException() throws JsonProcessingException,Exception
	{
		Mockito.when(associateServiceImpl.updateAssociate(1, associateDto)).thenReturn(associateDto);
		mockMvc.perform(put("/associates/id").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isBadRequest()).andReturn();
	}
}
