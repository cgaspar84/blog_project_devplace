/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fidelitytechnologies.training.blogapp.model.User;

/**
 * @author cgaspar
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

		

}
