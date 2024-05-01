package com.example.todowithauth.util;

import com.example.todowithauth.model.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenUtil {

  private String secret; // Replace with your secret key
  private long expirationTimeInMillis; // Set token expiration time in milliseconds

  // Constructor (inject secret key and other configs)

  public String generateAccessToken(CustomUser user) {
    Claims claims = (Claims) Jwts.claims().setSubject(user.getUsername());
    if (user.getRoles() != null) { // Check if roles exist before adding
      claims.put("roles", user.getRoles());
    }

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date()) // Set issued at time (recommended)
        .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis)) // Use direct expiration calculation
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  // Additional methods for token validation, refresh, etc. (optional)
}

