package com.app.restapi.models.dataManagement;

import java.util.HashMap;

import com.app.restapi.models.User;

public class DataUser {

	public static User map(HashMap<String,Object> other) {
		User user = new User();
		user.setCodeName( (String) other.get("codeName"));
		user.setFirstname( (String) other.get("firstname"));
		user.setLastname( (String) other.get("lastname"));
		user.setAge( (Integer) other.get("age"));
		user.setEmail( (String) other.get("email"));
		user.setTel( (String) other.get("tel"));
		user.setDateCreation( (String) other.get("dateCreation"));
		user.setDateModification( (String) other.get("dateModification"));
		return user;
	}
	
	public static HashMap<String,Object> map(User user) {
		HashMap<String,Object> into = new HashMap<>();
		into.put("id", user.getId());
		into.put("codeName", user.getCodeName());
		into.put("firstname", user.getFirstname());
		into.put("lastname",user.getLastname());
		into.put("age",user.getAge());
		into.put("email", user.getEmail());
		into.put("tel", user.getTel());
		into.put("dateCreation",user.getDateCreation());
		into.put("dateModification",user.getDateModification());
		return into;
	}

}
