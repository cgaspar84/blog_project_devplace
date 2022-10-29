/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelitytechnologies.training.blogapp.model.User;
import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.repositories.UserRepository;

/**
 * @author cgaspar
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository user_repository;
	
	/**
	 * 
	 */
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto getUserProfile(Long id) {
		Optional<User> userFound = user_repository.findById(id);
		
		if (userFound.isEmpty()) {			
			return null;
		} else {
			ModelMapper mapper = new ModelMapper();
			UserDto userDTO = mapper.map(userFound.get(), UserDto.class);
			
			return userDTO;
		}
	}

}
