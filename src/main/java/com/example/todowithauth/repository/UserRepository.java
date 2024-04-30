package com.example.todowithauth.repository;

import com.example.todowithauth.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

  CustomUser findByUsername(String username);
}