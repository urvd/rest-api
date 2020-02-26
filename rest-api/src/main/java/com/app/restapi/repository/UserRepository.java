package com.app.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.app.restapi.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
}
