package com.fidelitytechnologies.training.blogapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelitytechnologies.training.blogapp.model.StandardUser;
import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.repositories.StandardUserRepository;

@Service
public class StandardUserService {
	
	@Autowired
	private StandardUserRepository user_repository; 

	public StandardUserService() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto createUser(UserDto newUser) {
		ModelMapper mapper = new ModelMapper();
		StandardUser user = mapper.map(newUser, StandardUser.class);
		
		this.user_repository.save(user);
		
		UserDto resul = mapper.map(user, UserDto.class);
		return resul;
	}

}
