/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author cgaspar
 *
 */
@Configuration
//@ConditionalOnProperty(prefix= "app.security.custom", name="enabled", havingValue="true")
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${security.enabled:false}")
	private boolean securityEnabled;
	
	/**
	 * 
	 */
	public WebSecurityConfiguration() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param disableDefaults
	 */
	public WebSecurityConfiguration(boolean disableDefaults) {
		super(true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		if (!securityEnabled) {
			// All URI are freely accesible
			web.ignoring().antMatchers("/**");
			return;
		} 
	}

}
