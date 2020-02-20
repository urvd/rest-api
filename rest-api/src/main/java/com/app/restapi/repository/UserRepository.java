package com.app.restapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.restapi.models.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	
}
