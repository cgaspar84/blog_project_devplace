/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fidelitytechnologies.training.blogapp.model.User;
import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.services.UserService;

/**
 * @author cgaspar
 *
 */
@Controller
public class LoginController {

	@Autowired
	public UserService user_service;
	
	/**
	 * 
	 */
	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/registration/user/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		user = user_service.createUser(user);
		
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}
	
	

}
