/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author cgaspar
 *
 */
@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="mobile")
	private String mobile;
	@Column(name="email")
	private String email;
	@Column(name="password_hash")
	private String password;
	@Column(name="registered_at")
	private Date registeredAt;
	@Column(name="last_login")
	private Date lastLogin;
	//@Lob
	@Column(name="intro")
	private String intro;
	//@Lob
	@Column(name="profile")
	private String profile; 
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	@OneToMany(mappedBy = "user")
	List<PostInteraction> postInteractions;
	
	/**
	 * 
	 */
	public User() {
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
	public User(String firstName, String lastName, String mobile, String email, String intro, String profile) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.intro = intro;
		this.profile = profile;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	

}
