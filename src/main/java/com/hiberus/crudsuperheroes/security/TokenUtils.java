package com.hiberus.crudsuperheroes.security;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
 
private static final String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 259200L;

public static String createToken(String nombre, String email) {
	
	long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
	Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

	Map<String, Object> extra = new HashMap<>();
	extra.put("nombre", nombre);

	return Jwts.builder().setSubject(email).setExpiration(expirationDate).addClaims(extra)
			.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).compact();
}
	
public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
	try {
		Claims claims = Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build().parseClaimsJws(token)
				.getBody();

		String email = claims.getSubject();
		return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

	} catch (JwtException e) {
		return null;
	}

}
	
}
