package com.example.todowithauth.repository;

import com.example.todowithauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email); // Optional method to find user by email

  // ... other custom methods for user retrieval (optional)
}