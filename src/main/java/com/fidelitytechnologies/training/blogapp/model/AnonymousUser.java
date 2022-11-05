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
@DiscriminatorValue("3")
public class AnonymousUser extends User {

	/**
	 * 
	 */
	public AnonymousUser() {
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
	public AnonymousUser(String firstName, String lastName, String mobile, String email, String intro, String profile) {
		super(firstName, lastName, mobile, email, intro, profile);
		// TODO Auto-generated constructor stub
	}

}
