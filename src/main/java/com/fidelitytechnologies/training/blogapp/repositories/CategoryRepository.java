/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.Category;

/**
 * @author cgaspar
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
