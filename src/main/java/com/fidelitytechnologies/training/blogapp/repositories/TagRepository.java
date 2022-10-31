/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.Tag;

/**
 * @author cgaspar
 *
 */
public interface TagRepository extends CrudRepository<Tag, Long> {

	public Optional<Tag> getByName(String name);	
}
