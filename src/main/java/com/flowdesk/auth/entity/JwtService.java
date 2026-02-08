package com.flowdesk.auth.entity;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	@Value("${auth.token.expire.time.sec}")
	 private int tokenExpireTime;
	 
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
	public static final String JWT_SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	public static final int MILI_TO_SECOND_UNIT = 1000 ;
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private boolean isTokenExpired(String token) {
		 Date expiration = extractExpiration(token);
		    // If expiration is null, the token is meant to never expire.
		    return expiration != null && expiration.before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSignKey()) // Correct method for setting the signing key
				.parseClaimsJws(token).getBody();
	}

	public boolean isValid(String token, UserDetails user) {
		String username = extractUsername(token);
 
		return (username.equals(user.getUsername())) && !isTokenExpired(token);
	}

	public void validateToken(final String token) {

		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

  
 
	public String generateToken(String userName, boolean withExpiration) {
		Map<String, Object> claims = new HashMap<>();
	    long expirationTime = withExpiration ? MILI_TO_SECOND_UNIT * tokenExpireTime : 0;
	    return createToken(claims, userName, expirationTime);	}

	protected String createToken(Map<String, Object> claims, String userName, Long expirationTime) {
	    JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).setSubject(userName)
	                                 .setIssuedAt(new Date(System.currentTimeMillis()))
	                                 .signWith(getSignKey(), SignatureAlgorithm.HS256);

	    if (expirationTime != 0) {
	        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + expirationTime));
	    }

	    return jwtBuilder.compact();
	}


	protected Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
 
}