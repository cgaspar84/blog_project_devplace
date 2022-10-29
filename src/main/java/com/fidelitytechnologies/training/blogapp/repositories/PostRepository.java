/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.Post;

/**
 * @author cgaspar
 *
 */
public interface PostRepository extends CrudRepository<Post, Long> {

	
	
	public List<Post> getByTitle(String title);
	
	public List<Post> getByPublished(Boolean published);
}
