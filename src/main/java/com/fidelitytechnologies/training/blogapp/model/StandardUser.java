/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author cgaspar
 *
 */
@Entity
@DiscriminatorValue("2")
public class StandardUser extends User {

	/**
	 * 
	 */
	public StandardUser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param mobile
	 * @param email
	 * @param intro
	 * @param profile
	 */
	public StandardUser(String firstName, String lastName, String mobile, String email, String intro, String profile) {
		super(firstName, lastName, mobile, email, intro, profile);
		// TODO Auto-generated constructor stub
	}

}
