package com.example.todowithauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

  private Long id;
  private String name;
  private boolean completed;

  // Constructeurs, getters, setters et autres méthodes
}