package com.app.restapi.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONStringer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.Utils.AutoGenerateIdUtils;
import com.app.restapi.Utils.DateUtils;
import com.app.restapi.constantes.Const;
import com.app.restapi.exceptions.UserException;
import com.app.restapi.models.User;
import com.app.restapi.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	UserRepository userRepository;
	public UserRestController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/all")
	@ResponseBody public HashMap<String,Object> getAllUser() {
		return (HashMap<String,Object>) userRepository.findAll();		
	}
	//@GetMapping("/filter/{@param}")
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") int id) throws UserException {
	    return userRepository.findById(id)
	             .orElseThrow(() -> new UserException(id));
	}
	
	@PostMapping("/new")
	User createNewUser(@RequestBody HashMap<String,Object> newUser) throws UserException{
//		Object age = newUser.get("age");
//		int ageUser = (int) age;
//		newUser.setDateNaissance(newUser.getDateNaissance());
//		newUser.setDateCreation(newUser.setDateCreation(dateCreation););
		User user = new User();
		//user.setId( (Integer) newUser.get("id"));
		user.setFirstname( (String) newUser.get("firstname"));
		user.setLastname( (String) newUser.get("lastname"));
		user.setAge( (Integer) newUser.get("age"));
		user.setEmail( (String) newUser.get("email"));
		user.setTel( (String) newUser.get("tel"));
		user.setDateNaissance( (String) newUser.get("dateNaissance"));
		user.setDateCreation( (String) newUser.get("dateCreation"));
		user.setDateModification( (String) newUser.get("dateModification"));
		return userRepository.save(user);
	}
	@PostMapping("/news")
	public List<User> createNewUsers( @Valid @RequestBody List<User> newUsers) {
		return (List<User>) userRepository.saveAll(newUsers);
	}
	 
	@PutMapping("/{id}")
	public User updateUser(@PathVariable(value = "id") int userId,

 @RequestBody User userModified) throws UserException {

		User userSearched  = new User();
		userSearched = userRepository.findById(userId)
		                .orElseThrow(() -> new UserException(userId));
		if(userModified.getFirstname() != null) userSearched.setFirstname(userModified.getFirstname());
		if(userModified.getLastname() != null) userSearched.setLastname(userModified.getLastname());
		if(userModified.getEmail() != null) userSearched.setEmail(userModified.getEmail());
		if(userModified.getAge() != userSearched.getAge()) userSearched.setAge(userModified.getAge());
		if(userModified.getTel() != userSearched.getTel()) userSearched.setTel(userModified.getTel());
	
		User updatedUser = userRepository.save(userSearched);

		return updatedUser;
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId) throws UserException {
		User userOver = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(userId));

		userRepository.delete(userOver);

		return ResponseEntity.ok().build();
    }
	
}
