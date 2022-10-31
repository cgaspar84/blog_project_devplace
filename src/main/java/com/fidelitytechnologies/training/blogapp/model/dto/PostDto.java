/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.fidelitytechnologies.training.blogapp.model.Post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cgaspar
 *
 */
@Getter
@Setter
@Data
public class PostDto implements FactoryDTO {

	private Long id;
	private String title;
	private String content;
	private String metaTitle;
	private String summary;
	private String imageUrl;
	
	private List<CategoryDto> categories;
	private List<TagDto> tags;
	
	/**
	 * 
	 */
	public PostDto() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

	
	@Override
	public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
		mapper.addMappings(postMap(utils));
		
		return mapper;
	}
	
	public PropertyMap<Post, PostDto> postMap(MappingUtils utils) {
		return new PropertyMap<Post, PostDto>() {
			@Override
			protected void configure() {
				/*
				Converter<Post, Integer> getSize = new AbstractConverter<Post, Integer>() {
					@Override
					protected Integer convert(Post post) {
						return post.getTags().size();
					}
				};*/
				
				Converter<Post, List<TagDto>> mapTags = new AbstractConverter<Post, List<TagDto>>() {

					@Override
					protected List<TagDto> convert(Post post) {
						return utils.mapList(new ArrayList<>(post.getTags()), TagDto.class);
					}
									
				};
				
				Converter<Post, List<CategoryDto>> mapCategories = new AbstractConverter<Post, List<CategoryDto>>() {
					
					@Override
					protected List<CategoryDto> convert(Post post) {
						return utils.mapList(new ArrayList<>(post.getCategories()), CategoryDto.class);
					}
				};
				
				
				//using(getSize).map(source, destination.get);
				using(mapTags).map(source, destination.getTags());
				using(mapCategories).map(source, destination.getCategories());
				
			}
		};
	}
	
	
}
