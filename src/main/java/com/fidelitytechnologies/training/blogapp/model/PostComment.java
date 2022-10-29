/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "POST_COMMENT")
public class PostComment {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="title")
	private String title;
	@Column(name="published")
	private Boolean published;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="published_at")
	private Date publishedAt;
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name="fk_comment_post"))
	private Post post;
	
	/**
	 * 
	 */
	public PostComment() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
