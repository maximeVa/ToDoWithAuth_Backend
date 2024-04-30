package com.example.todowithauth.repository;

import com.example.todowithauth.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

  CustomUser findByEmail(String email); // Optional method to find user by email

  CustomUser findByUsername(String username);
}