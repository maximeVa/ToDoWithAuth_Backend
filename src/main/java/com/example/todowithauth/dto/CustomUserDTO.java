package com.example.todowithauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomUserDTO {

  private Long id;
  private String username;
  private String email;
  private String password;
  private String token;

  public CustomUserDTO(String username, String email) {
    this.username = username;
    this.email = email;
  }
}