/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fidelitytechnologies.training.blogapp.services.DataUserDetailsService;

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
	
	@Autowired
	private DataUserDetailsService userDetailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
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
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		if (!securityEnabled) {
			// All URI are freely accesible
			web.ignoring().antMatchers("/**");
			return;
		}
		
		web.ignoring().antMatchers("/resources/**",
				"/static/**",
				"/css/**",
				"/js/**",
				"/img/**");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginPage = "/login";
		String logoutPage = "/logout";
		
		http.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers(loginPage).permitAll()
			.antMatchers("/registration").permitAll()
			//.antMatchers(null)
			.anyRequest()
			.authenticated()
			.and().csrf().disable()
			.formLogin()
			.loginPage(loginPage)
			//.successHandler(successHandler)
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
			.logoutSuccessUrl(loginPage).and().exceptionHandling();
			
	}
}
