/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model.dto;

/**
 * @author cgaspar
 *
 */
public class CategoryDto implements FactoryDTO {

	private Long id;
	private String name;
	private String description;
	/**
	 * 
	 */
	public CategoryDto() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
