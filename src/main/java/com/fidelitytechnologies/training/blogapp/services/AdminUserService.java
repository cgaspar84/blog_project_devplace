/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fidelitytechnologies.training.blogapp.model.AdminUser;
import com.fidelitytechnologies.training.blogapp.model.User;
import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.repositories.AdminUserRepository;

/**
 * @author cgaspar
 *
 */
@Service
public class AdminUserService {

	@Autowired
	private AdminUserRepository user_repository;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 
	 */
	public AdminUserService() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto createUser(UserDto newUser) {
		ModelMapper mapper = new ModelMapper();
		AdminUser user = mapper.map(newUser, AdminUser.class);
		
		// Generate encrypt user password
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		// Assign roles
		
		this.user_repository.save(user);
		
		UserDto resul = mapper.map(user, UserDto.class);
		return resul;
	}
	
	public UserDto getUserProfile(Long id) {
		Optional<AdminUser> userFound = user_repository.findById(id);
		
		if (userFound.isEmpty()) {			
			return null;
		} else {
			ModelMapper mapper = new ModelMapper();
			UserDto userDTO = mapper.map(userFound.get(), UserDto.class);
			
			return userDTO;
		}
	}
	
	public User getUserData(String username) {
		Optional<AdminUser> userFound = user_repository.getByUsername(username);
		
		if (!userFound.isPresent()) {
			return null;
		}
		return userFound.get();
	}
	
	public void deleteUserByID(Long id) {
		user_repository.deleteById(id);
	}

}
