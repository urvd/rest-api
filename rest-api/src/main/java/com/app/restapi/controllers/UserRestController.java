package com.app.restapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.exceptions.NotFoundException;
import com.app.restapi.models.User;
import com.app.restapi.repository.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	IUserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUser() {
		return userRepository.findAll();		
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable(value = "id") Integer id) throws NotFoundException {
	    return userRepository.findById(id)
	             .orElseThrow(() -> new NotFoundException(id));
	}
	 
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable(value = "id") Integer userId,
	                           @Valid @RequestBody User userModified) throws NotFoundException {

		User userSearched  = new User();
		userSearched = userRepository.findById(userId)
		                .orElseThrow(() -> new NotFoundException(userId));
	
		if(userModified.getFirstname() != null) userSearched.setFirstname(userModified.getFirstname());
		if(userModified.getLastname() != null) userSearched.setLastname(userModified.getLastname());
		if(userModified.getEmail() != null) userSearched.setEmail(userModified.getEmail());
		if(userModified.getAge() != userSearched.getAge()) userSearched.setAge(userModified.getAge());
		if(userModified.getTel() != userSearched.getTel()) userSearched.setTel(userModified.getTel());
	
		User updatedUser = userRepository.save(userSearched);

		return updatedUser;
	}
	
	@DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Integer userId) throws NotFoundException {
		User userOver = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));

		userRepository.delete(userOver);

		return ResponseEntity.ok().build();
    }
	
}
