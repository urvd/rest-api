package com.app.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.controllers.UserRestController;
import com.app.restapi.repository.UserRepository;

@SpringBootApplication
@RestController
public class RestApiApplication {

	@RequestMapping("/")
	public String hello() {
		return "Hello the world !!!";
	}
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
