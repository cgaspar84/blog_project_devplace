/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.services.AdminUserService;
import com.fidelitytechnologies.training.blogapp.services.StandardUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author cgaspar
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private StandardUserService regularUserService;
	
	//@Autowired
	//private AnonymousUserService anonymousUserService;
	
	/**
	 * 
	 */
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@ApiOperation(value="Registrar un usuario")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message="CREATED. El usuario fue registrado", response = UserDto.class),
			@ApiResponse(code = 404, message="Error al crear nuevo usuario")
	})
	@GetMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		
		switch (user.getKind()) {
		case 1:
			user = adminUserService.createUser(user);
			break;
		case 2:
			user = regularUserService.createUser(user);
			break;
		default:
			user = regularUserService.createUser(user);
			break;
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Eliminar un user")
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		adminUserService.deleteUserByID(id);
		return "Delete OK";
	}
}
