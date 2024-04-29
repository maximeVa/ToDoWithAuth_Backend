package com.example.todowithauth.service;

import com.example.todowithauth.model.User;
import com.example.todowithauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void registerUser(String name, String email, String password) {
    String hashedPassword = passwordEncoder.encode(password);
    User user = new User(name, email, hashedPassword);
    userRepository.save(user);
  }

  // ... other user management methods (optional)
}