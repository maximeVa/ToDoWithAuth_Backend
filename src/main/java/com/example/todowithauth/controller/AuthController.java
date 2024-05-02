package com.example.todowithauth.controller;

import com.example.todowithauth.dto.CustomUserDTO;
import com.example.todowithauth.model.CustomUser;
import com.example.todowithauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<CustomUserDTO> login() {
    // Access the authenticated user (using Spring Security mechanisms)
    // This will be available through SecurityContextHolder or another mechanism
    // depending on your Spring Security configuration
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomUser loggedInUser = (CustomUser) authentication.getPrincipal();

    // Optional token generation (if implementing JWT)
    String token = userService.generateToken(loggedInUser);

    // Return successful login response with user details (and token if applicable)
    CustomUserDTO responseUser = new CustomUserDTO(loggedInUser.getUsername(), loggedInUser.getEmail());
    if (token != null) {
      responseUser.setToken(token);
    }
    return ResponseEntity.ok(responseUser);
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody CustomUserDTO userDTO) {
    // Call UserService method to register the user with DTO
    boolean registrationSuccess = userService.saveUser(userDTO);

    if (registrationSuccess) {
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user");
    }
  }

  // You can add methods for register and logout here
}