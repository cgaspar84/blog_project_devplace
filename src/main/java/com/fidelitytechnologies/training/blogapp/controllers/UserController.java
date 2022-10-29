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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fidelitytechnologies.training.blogapp.model.dto.PostDto;
import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.services.PostService;
import com.fidelitytechnologies.training.blogapp.services.UserService;

/**
 * @author cgaspar
 *
 */
@RestController
@RequestMapping("/registered_area")
public class UserController {

	@Autowired
	private UserService userService; 
	
	@Autowired
	private PostService postService;
	
	/**
	 * 
	 */
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/getProfile/{id}")
	public ResponseEntity<UserDto> getUserByID(@PathVariable("id") Long id) {
		UserDto user = userService.getUserProfile(id);
		
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User was not found!");
		} else {
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		}
	}
	
	@PostMapping("/post/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
		post = postService.createPost(null, post);
		
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	@PutMapping("/post/edit/{id}")
	public ResponseEntity<PostDto> editPost(@PathVariable Long id, @RequestBody PostDto postDetail) {
		postDetail = postService.modifyPost(null, id, postDetail);
	
		return new ResponseEntity<PostDto>(postDetail, HttpStatus.OK);
	}
	
	@DeleteMapping("/post/delete/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		postService.deletePostByID(null, id);
		return "Delete OK";
	}
	

}
