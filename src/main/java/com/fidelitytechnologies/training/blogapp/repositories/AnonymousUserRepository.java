/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.AnonymousUser;

/**
 * @author cgaspar
 *
 */
public interface AnonymousUserRepository extends CrudRepository<AnonymousUser, Long> {

}
