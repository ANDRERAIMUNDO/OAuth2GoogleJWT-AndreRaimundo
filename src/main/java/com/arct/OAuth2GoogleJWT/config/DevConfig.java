package com.arct.OAuth2GoogleJWT.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arct.OAuth2GoogleJWT.services.StartDataBaseService;

@Configuration
public class DevConfig {
    
    @Autowired
    private StartDataBaseService startDataBaseService;


	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
    
    @Bean
    public boolean instantiateDataBase () throws ParseException {
       if (!"create".equals(strategy)){
			return true;
		}
        startDataBaseService.instantiateTestDataBase();
        return true;
    }
}