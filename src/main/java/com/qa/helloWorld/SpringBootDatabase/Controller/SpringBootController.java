package com.qa.helloWorld.SpringBootDatabase.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.helloWorld.SpringBootDatabase.Exception.SpringBootException;
import com.qa.helloWorld.SpringBootDatabase.Model.SpringBootData;
import com.qa.helloWorld.SpringBootDatabase.Repository.SpringBootRepo;

@RestController
@RequestMapping("/api")
public class SpringBootController {

	@Autowired
	SpringBootRepo springBootRepo; 
	
	@GetMapping("/person")
	public List<SpringBootData> getAllPeople()
	{
		return springBootRepo.findAll(); 
	}
	
	
	@PostMapping("/test")
	public SpringBootData createPerson(@Valid @RequestBody SpringBootData spd)
	{	
		return springBootRepo.save(spd); 
	}
	
	@PutMapping("/person/{id}")
	public SpringBootData updatePerson(@PathVariable(value = "id")Long personID,
			@Valid @RequestBody SpringBootData personalDetails)
	{	
		SpringBootData spd = springBootRepo.findById(personID).orElseThrow(()-> new SpringBootException("Person", "id", personID));
		
		spd.setName(personalDetails.getName());
		spd.setAddress(personalDetails.getAddress());
		spd.setAge(personalDetails.getAge());
		
		SpringBootData updateData = springBootRepo.save(spd);
		return updateData; 
	}
	
	
	@GetMapping("/person/{id}")
	public SpringBootData getPersonByID(@PathVariable(value = "id")Long personID)
	{
		return springBootRepo.findById(personID).orElseThrow(() -> new SpringBootException("SpringBootData", "id", personID));
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long personID)
	{
		SpringBootData spd = springBootRepo.findById(personID).orElseThrow(()-> new SpringBootException("Person", "id", personID));
		
				springBootRepo.delete(spd);
		return ResponseEntity.ok().build(); 
	}
	
}
