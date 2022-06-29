package com.arct.OAuth2GoogleJWT.services.security;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {
    
    //@Value("${jwt.secret}")
    //private String secret;

	//@Value("${jwt.expiration}")
	//private Long expiration;

    public static long EXPIRATION_TIME = SecurityConstants.EXPIRATION_TIME;

    public static String SECRET = SecurityConstants.SECRET;

    public String generateToken (String username) {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET.getBytes())); //.compact();
    }

    public boolean tokenValido(String token) {  
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

    public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

    private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET.getBytes())
            .parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
}
