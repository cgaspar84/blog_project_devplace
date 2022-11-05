/**
 * 
 */
package com.fidelitytechnologies.training.blogapp.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author cgaspar
 *
 */
@Component
public class AuthCustom implements AuthenticationSuccessHandler {

	
	
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String redirectAddr = "/home";
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		for (GrantedAuthority gt: authorities) {
			if (gt.getAuthority().equals("STANDARD")) {
				redirectAddr = "/standard/home";
				
			} else if (gt.getAuthority().equals("ADMIN")) {
				redirectAddr = "/admin/home";
			}
		}

	}

}
