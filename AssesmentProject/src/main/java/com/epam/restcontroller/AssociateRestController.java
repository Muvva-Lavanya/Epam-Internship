package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.AssociateDto;
import com.epam.service.AssociateServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("associates")
@Slf4j
public class AssociateRestController {
	
	@Autowired
	AssociateServiceImpl associateServiceImpl;
	
	@PostMapping
	public ResponseEntity<AssociateDto> createAssociate(@Valid @RequestBody AssociateDto associateDto)
	{
		log.info("Entered into create Associate Method RestController :{}",associateDto);
		return new ResponseEntity<>(associateServiceImpl.createAssociate(associateDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{gender}")
	public ResponseEntity<List<AssociateDto>> getAssociatesByGender(@PathVariable String gender)
	{
		log.info("Entered into get Associates By Gender RestController");
		return new ResponseEntity<>(associateServiceImpl.getAllAssociatesByGender(gender),HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAssociate(@PathVariable int id)
	{
		log.info("Entered into delete Associate Method RestController");
		associateServiceImpl.deleteAssociate(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AssociateDto> updateAssociate(@PathVariable int id,@Valid @RequestBody AssociateDto associateDto)
	{
		log.info("Entered into update Associate Method RestController :{}",associateDto);
		return new ResponseEntity<>(associateServiceImpl.updateAssociate(id, associateDto),HttpStatus.OK);
	}
	

}
