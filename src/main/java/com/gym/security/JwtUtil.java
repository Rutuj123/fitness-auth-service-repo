package com.gym.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
private final String SECRET="MySuperSecretKeyForJwtWhichIsVerySecure12345";
public String generateToken(String username, String role) {
	return Jwts.builder()
			.setSubject(username)
			.claim("role", role)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
			.signWith(Keys.hmacShaKeyFor(SECRET.getBytes()),SignatureAlgorithm.HS256)
			.compact();
}

public String extractUsername(String token) {
	return getClaims(token).getSubject();
}

public Claims getClaims(String token) {
	return Jwts.parserBuilder()
			.setSigningKey(SECRET.getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody();
}
}
