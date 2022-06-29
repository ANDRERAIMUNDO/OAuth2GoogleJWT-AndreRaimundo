package com.arct.OAuth2GoogleJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OAuth2GoogleJwtApplication {

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

	public void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	authenticationManagerBuilder.userDetailsService(userDetailsService)
	.passwordEncoder(bCryptPasswordEncoder());
}
	public static void main(String[] args) {
		SpringApplication.run(OAuth2GoogleJwtApplication.class, args);
	}
}
