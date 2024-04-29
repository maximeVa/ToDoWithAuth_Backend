package com.example.todowithauth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  private String password; // Hashed password

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password; // Remember, password should be hashed before saving
  }

  // ... other attributes and relationships (optional)
}
