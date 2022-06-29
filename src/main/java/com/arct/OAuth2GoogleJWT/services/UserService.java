package com.arct.OAuth2GoogleJWT.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.arct.OAuth2GoogleJWT.services.security.UserSS;

public class UserService {
    
    public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}