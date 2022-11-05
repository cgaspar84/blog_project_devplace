/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.services.AdminUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author cgaspar
 *
 */
@Controller
public class LoginController {

	@Autowired
	private AdminUserService adminUserService;
	/**
	 * 
	 */
	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@PostMapping("/registration")
	public String createUser(UserDto user, Model model) {
		//TODO Validate user if exists
		
		user = adminUserService.createUser(user);
		
		return "register";
	}

}
