/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cgaspar
 *
 */
@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

	/**
	 * 
	 */
	public SecurityConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
