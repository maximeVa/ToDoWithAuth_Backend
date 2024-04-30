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

  // Constructeurs, getters, setters et autres méthodes
}