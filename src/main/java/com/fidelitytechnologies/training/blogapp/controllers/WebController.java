/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fidelitytechnologies.training.blogapp.model.dto.PostDto;
import com.fidelitytechnologies.training.blogapp.services.PostService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Main web controller for registered/unregistered users
 * 
 * @author cgaspar
 *
 */
@Controller
@RequestMapping("/index")
public class WebController {

	@Autowired
	private PostService postService;
	
	/*
	@GetMapping("/")
	public String viewHomePage(Model model) {
		//model
		
		return "redirect:/index";
	}*/
	
	@ApiOperation(value = "Web. Pagina index")	
	@ApiResponse(code = 200, message="Template Index")
	@GetMapping("/home")
	public String index(Model model) {
		
		List<PostDto> postList = postService.getAllPostsPublished();
		
		model.addAttribute("posts", postList);
		return "admin/index";
	}
		
	@GetMapping("/post/getAll") 
	public List<PostDto> getAllPosts() {	
		
		return postService.getAllPostsPublished();
	}		
	
	

}
