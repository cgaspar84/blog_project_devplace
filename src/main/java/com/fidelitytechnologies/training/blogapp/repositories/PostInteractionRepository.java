/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.PostInteraction;

/**
 * @author cgaspar
 *
 */
public interface PostInteractionRepository extends CrudRepository<PostInteraction, Long> {

}
