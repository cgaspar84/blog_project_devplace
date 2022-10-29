/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelitytechnologies.training.blogapp.errors.ValidateException;
import com.fidelitytechnologies.training.blogapp.model.Category;
import com.fidelitytechnologies.training.blogapp.model.Post;
import com.fidelitytechnologies.training.blogapp.model.Tag;
import com.fidelitytechnologies.training.blogapp.model.dto.CategoryDto;
import com.fidelitytechnologies.training.blogapp.model.dto.MappingUtils;
import com.fidelitytechnologies.training.blogapp.model.dto.PostDto;
import com.fidelitytechnologies.training.blogapp.model.dto.TagDto;
import com.fidelitytechnologies.training.blogapp.model.dto.UserDto;
import com.fidelitytechnologies.training.blogapp.repositories.CategoryRepository;
import com.fidelitytechnologies.training.blogapp.repositories.PostInteractionRepository;
import com.fidelitytechnologies.training.blogapp.repositories.PostRepository;
import com.fidelitytechnologies.training.blogapp.repositories.TagRepository;

/**
 * @author cgaspar
 *
 */
@Service
public class PostService {

	@Autowired
	private PostRepository post_repository;
	
	@Autowired
	private TagRepository tag_repository;
	
	@Autowired
	private CategoryRepository category_repository;
	
	@Autowired
	private PostInteractionRepository post_interation_repository;
	
	/**
	 * 
	 */
	public PostService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<PostDto> getAllPosts() {
		List<Post> findAll = (List<Post>) post_repository.findAll();
		
		List<PostDto> result = new ArrayList<>();
		Iterator<Post> itPosts = findAll.iterator();
		ModelMapper mapper = new ModelMapper();
		while (itPosts.hasNext()) {
			PostDto postItem = mapper.map(itPosts.next(), PostDto.class);
			
			result.add(postItem);
		}
		
		return result;
		
	}
	
	public List<PostDto> getAllPostsPublished() {
		
		List<Post> postFound = post_repository.getByPublished(true);		
		
		List<PostDto> result = new ArrayList<>();
				
		MappingUtils utils = new MappingUtils();
		result = utils.mapList(postFound, PostDto.class);
		
		return result;
	}
	
	public PostDto createPost(UserDto user, PostDto newPost) {
		//TODO Validar post con user
		
		ModelMapper mapper = new ModelMapper();
		Post post = mapper.map(newPost, Post.class);
				
		// Set post properties
		post.setPublished(true);
		post.setCreatedAt(new Date());
		post.setPublishedAt(new Date());
		post.setTags(new ArrayList<>());
		post.setCategories(new ArrayList<>());
		
		// Set list of tags
		if ((newPost.getTags() != null) && (!newPost.getTags().isEmpty())) {
			Iterator<TagDto> itTagsIDs = newPost.getTags().iterator();
			
			while (itTagsIDs.hasNext()) {
				TagDto currentTagDto = itTagsIDs.next();
				// Get tag from DB
				Optional<Tag> foundTag = tag_repository.findById(currentTagDto.getId());
				
				if (foundTag.isPresent()) {
					post.addTag(foundTag.get());
				} else {
					throw new ValidateException("No such tag found");
				}
			}
			
			
		}
		// Set list of categories
		if ((newPost.getCategories() != null) && (!newPost.getCategories().isEmpty())) {
			Iterator<CategoryDto> itCategoriesIDs = newPost.getCategories().iterator();
			
			while (itCategoriesIDs.hasNext()) {
				CategoryDto currentCategoryDto = itCategoriesIDs.next();
				// Get Category from DB
				Optional<Category> foundCategory = category_repository.findById(currentCategoryDto.getId());
				
				if (foundCategory.isPresent()) {
					post.addCategoy(foundCategory.get());
				} else {
					throw new ValidateException("No such category found");
				}
			}
		}
		
		this.post_repository.save(post);
		
		PostDto resul = mapper.map(post, PostDto.class);		
		return resul;
	}
	
	public PostDto modifyPost(UserDto user, Long id, PostDto modifyPost) {
		//TODO Validar post con user
		
		ModelMapper mapper = new ModelMapper();
		//Post post = mapper.map(modifyPost,  Post.class);
		Optional<Post> searchPost = post_repository.findById(id);
		
		Post updatePost = null;
		if (!searchPost.isEmpty()) {
			updatePost = searchPost.get();
			
			updatePost.setTitle(modifyPost.getTitle());
			updatePost.setMetaTitle(modifyPost.getMetaTitle());
			updatePost.setSummary(modifyPost.getSummary());
			updatePost.setContent(modifyPost.getContent());
			
			post_repository.save(updatePost);
		} else {
			throw new ValidateException("Invalid post ID");
		}
		
		PostDto resul = mapper.map(updatePost, PostDto.class);
		return resul;
	}
	
	public void deletePostByID(UserDto user, Long id) {
		post_repository.deleteById(id);	
	}
	
	
	
	public void doPostLike(UserDto user, Long postID) {
		
	}
	
	public void doPostDislike(UserDto user, Long postID) {
		
	}
	
	public PostDto getPostByID(Long id) {
		Optional<Post> postFound = post_repository.findById(id);
		
		if (postFound.get() != null) {
			ModelMapper mapper = new ModelMapper();
			PostDto postDTO = mapper.map(postFound.get(), PostDto.class);
			
			return postDTO;
		} else {
			throw new ValidateException("No post found");
		}
		
	}
	
	public List<PostDto> getPostByTitle(String title) {
		
		List<Post> postList = post_repository.getByTitle(title);
		
		List<PostDto> result = new ArrayList<>();
		
		MappingUtils utils = new MappingUtils();
		result = utils.mapList(postList, PostDto.class);
		
		return result;
	}
	

}
