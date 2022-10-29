/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.Tag;

/**
 * @author cgaspar
 *
 */
public interface TagRepository extends CrudRepository<Tag, Long> {

}
