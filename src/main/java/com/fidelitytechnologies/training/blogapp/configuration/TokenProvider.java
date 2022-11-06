package com.fidelitytechnologies.training.blogapp.configuration;

import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.JwtBuilder;

public class TokenProvider {

	public static JwtBuilder generateToken(Authentication auth) {
		final String authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		//TODO Falta
		return null;
	}

}
