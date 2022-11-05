/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.StandardUser;

/**
 * @author cgaspar
 *
 */
public interface StandardUserRepository extends CrudRepository<StandardUser, Long> {

}
