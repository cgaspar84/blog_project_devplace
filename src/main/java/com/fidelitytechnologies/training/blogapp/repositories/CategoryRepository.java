/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.Category;

/**
 * @author cgaspar
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

	public Optional<Category> getByName(String name);
}
