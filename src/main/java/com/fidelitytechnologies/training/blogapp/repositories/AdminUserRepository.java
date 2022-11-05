/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fidelitytechnologies.training.blogapp.model.AdminUser;
import com.fidelitytechnologies.training.blogapp.model.User;

/**
 * @author cgaspar
 *
 */
public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {


	public Optional<AdminUser> getByUsername(String username);

}
