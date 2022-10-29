/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.model.dto;

import org.modelmapper.ModelMapper;

/**
 * @author cgaspar
 *
 */
public interface FactoryDTO {

	default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
		return mapper;
	}
}
