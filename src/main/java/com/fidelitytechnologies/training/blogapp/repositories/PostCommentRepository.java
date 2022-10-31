/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.PostComment;

/**
 * @author cgaspar
 *
 */
public interface PostCommentRepository extends CrudRepository<PostComment, Long> {

	
}
