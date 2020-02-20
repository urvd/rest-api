package com.app.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.models.User;

public interface IUserRepository extends JpaRepository<User,Integer>{


	
}
