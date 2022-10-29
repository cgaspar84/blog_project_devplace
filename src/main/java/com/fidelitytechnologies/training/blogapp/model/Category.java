/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cgaspar
 *
 */
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="CATEGORY")
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	@ManyToMany(mappedBy = "categories")
	private List<Post> posts = new ArrayList<>();
	
	
	
	/**
	 * 
	 */
	public Category() {
		// TODO Auto-generated constructor stub
	}



	public List<Post> getPosts() {
		return posts;
	}



	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	

}
