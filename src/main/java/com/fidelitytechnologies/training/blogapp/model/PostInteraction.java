/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author cgaspar
 *
 */
@Entity
@Table(name="POST_INTERACTION")
public class PostInteraction {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="flags")
	private Integer flags;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;	
	
	/**
	 * 
	 */
	public PostInteraction() {
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Integer getFlags() {
		return flags;
	}



	public void setFlags(Integer flags) {
		this.flags = flags;
	}
	
	
	
}
