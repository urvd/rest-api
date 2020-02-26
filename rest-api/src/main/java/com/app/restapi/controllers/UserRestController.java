package com.app.restapi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.mapping.Array;
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
import com.app.restapi.constantes.Const.ErrorCode;
import com.app.restapi.exceptions.UserException;
import com.app.restapi.models.User;
import com.app.restapi.models.dataManagement.DataUser;
import com.app.restapi.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	UserRepository userRepository;
	private boolean userIsModified;
	private List<User> users;
	public UserRestController(UserRepository userRepository) {
		this.userRepository = userRepository;
		userIsModified = false;
		users = this.userRepository.findAll();
	}

	
	/**
	 * @return the users
	 */
	private List<User> getUsers() {
		return users;
	}
//
//
//	/**
//	 * @param users the users to set
//	 */
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}


	@GetMapping("/all")
	@ResponseBody public List<User> getAllUser() {
		return getUsers();		
	}
	@GetMapping("/filter/{param}")
	public List<User> getUserByParam(@PathVariable Object param) {
		//List<HashMap<String,Object>> match;
		List<User> users = getUsers();
		List<User> catchUsers = new ArrayList<>();
		users.forEach(u -> {
			HashMap<String,Object> match = DataUser.map(u);
			if(match.containsValue(param)) {
					catchUsers.add(u);
			}
		});
		return catchUsers;
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") Integer id) throws UserException {
	    return userRepository.findById(id)
	             .orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND));
	}
	
	@PostMapping("/new")
	User createNewUser(@RequestBody HashMap<String,Object> newUser) throws UserException{
		checkSomeUsersValuesExist(newUser);
		newUser.put("codeName", AutoGenerateIdUtils.generateNumbers(Const.LENGHT_ID));
		newUser.put("dateCreation", DateUtils.setDate().toString());
		newUser.put("dateModification", "");
		User user = DataUser.map(newUser);
		return userRepository.save(user);
	}

	@PostMapping("/news")
	public List<User> createNewUsers(@RequestBody List<HashMap<String,Object>> newUsers) throws UserException {
		List<User> mapU = new ArrayList<>();
		for(HashMap<String,Object> u:newUsers) {
			mapU.add(createNewUser(u));
 		}
		return mapU;
	}
    private void checkSomeUsersValuesExist(HashMap<String,Object> user) throws UserException{
    	boolean existUserEmail = !getUserByParam(user.get("email")).isEmpty(); 
    	boolean existUserNomPrenom = (!getUserByParam(user.get("firstname")).isEmpty() && !getUserByParam(user.get("lastname")).isEmpty());
    	boolean existUserTel = !getUserByParam(user.get("tel")).isEmpty();
    	
    	//Message erreur pour les trois cas
		if(existUserEmail && existUserNomPrenom && existUserTel) {
			throw new UserException(ErrorCode.EXIST);
		}

		//Message pour 2/3 cas
		if(existUserNomPrenom && existUserTel){
			throw new UserException(ErrorCode.EXIST_NOM_PRENOM,ErrorCode.EXIST_TEL);
		}
		if(existUserEmail && existUserNomPrenom) {
			throw new UserException(ErrorCode.EXIST_MAIL,ErrorCode.EXIST_NOM_PRENOM);
		}
		if(existUserEmail && existUserTel) {
			throw new UserException(ErrorCode.EXIST_MAIL,ErrorCode.EXIST_TEL);
		}
		
    	//Message erreur independament des les trois cas
		if(existUserEmail) {
			throw new UserException(ErrorCode.EXIST_MAIL);
		}
		if(existUserNomPrenom) {
			throw new UserException(ErrorCode.EXIST_NOM_PRENOM);
		}
		if(existUserTel) {
			throw new UserException(ErrorCode.EXIST_TEL);
		}
    }
	 
	@PutMapping("/{id}")
	public User updateUser(@PathVariable(value = "id") Integer userId,
						@RequestBody HashMap<String,Object> userMap) throws UserException {
		User userModified = DataUser.map(userMap);
		User userSearched  = new User();
		userSearched = userRepository.findById(userId)
		                .orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND));

		if(!userModified.getFirstname().equals(userSearched.getFirstname()) || Strings.isNotBlank(userModified.getFirstname())){
			userSearched.setFirstname(userModified.getFirstname());
			setUserIsModified(true);
		}
		if(!userModified.getLastname().equals(userSearched.getLastname()) || Strings.isNotBlank(userModified.getLastname())) {
			userSearched.setLastname(userModified.getLastname());
			setUserIsModified(true);
		}
		if(!userModified.getEmail().equals(userSearched.getFirstname()) || Strings.isNotBlank(userModified.getLastname())) {
			userSearched.setEmail(userModified.getEmail());
			setUserIsModified(true);
		}
		if(userModified.getAge() != userSearched.getAge() || userModified.getAge()!=null || userModified.getAge()!=0) {
			userSearched.setAge(userModified.getAge());
			setUserIsModified(true);
		}
		if(!userModified.getTel().equals(userSearched.getFirstname()) || Strings.isNotBlank(userModified.getTel())) {
			userSearched.setTel(userModified.getTel());
			setUserIsModified(true);
		}	
		
		if(userIsModified) {
			checkSomeUsersValuesExist(DataUser.map(userSearched));
			userSearched.setDateModification(DateUtils.setDate().toString());
			User updatedUser = userRepository.save(userSearched);
			return updatedUser;
		}
		//throw new UserException(ErrorCode.EXIST);
		return null;

	}
	private void setUserIsModified(boolean isModified){
		userIsModified = isModified;
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) throws UserException {
		User userOver = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND));

		userRepository.delete(userOver);

		return ResponseEntity.ok().build();
    }
	
}
