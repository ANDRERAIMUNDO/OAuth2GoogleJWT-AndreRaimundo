
package com.arct.OAuth2GoogleJWT.services.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arct.OAuth2GoogleJWT.DTO.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public static long EXPIRATION_TIME = SecurityConstants.EXPIRATION_TIME;

    public static String SECRET = SecurityConstants.SECRET;

    public JWTAuthenticationFilter (AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new  JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, 
    HttpServletResponse response) throws AuthenticationException {
       try {
           CredenciaisDTO credenciaisDTO = new ObjectMapper()
            .readValue(request.getInputStream(),
             CredenciaisDTO.class);
           UsernamePasswordAuthenticationToken authenticationToken = new 
                UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(),
                 credenciaisDTO.getPassword(), new ArrayList<>());
           Authentication authentication = authenticationManager.authenticate(authenticationToken);
           return authentication;
       }     
            catch (IOException e) {
                throw new RuntimeException(e);
       }
    }
    
    @Override
    protected void successfulAuthentication ( HttpServletRequest request, 
    HttpServletResponse response, FilterChain chain,
    Authentication authentication) throws IOException, ServletException {

        String  username = ((UserSS) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
