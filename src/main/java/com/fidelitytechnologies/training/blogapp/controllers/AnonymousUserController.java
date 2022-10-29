/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelitytechnologies.training.blogapp.model.dto.PostDto;
import com.fidelitytechnologies.training.blogapp.services.PostService;

/**
 * @author cgaspar
 *
 */
@RestController
@RequestMapping("/unregistered")
public class AnonymousUserController {

	@Autowired
	private PostService postService;
	
	/**
	 * 
	 */
	public AnonymousUserController() {
		// TODO Auto-generated constructor stub
	}
	
		
	@GetMapping("/post/getAll") 
	public List<PostDto> getAllPosts() {	
		
		return postService.getAllPostsPublished();
	}
	
	
	@GetMapping("/post/get/{id}")
	public PostDto getPostSimple(@PathVariable("id") Long id) {
		return postService.getPostByID(id);		
	}

}
