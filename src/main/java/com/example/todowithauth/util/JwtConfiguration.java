package com.example.todowithauth.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expirationTimeInMillis}")
  private long expirationTimeInMillis;

  @Bean
  public JwtTokenUtil jwtTokenUtil() {
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    jwtTokenUtil.setSecret(secret);
    jwtTokenUtil.setExpirationTimeInMillis(expirationTimeInMillis);
    return jwtTokenUtil;
  }
}
