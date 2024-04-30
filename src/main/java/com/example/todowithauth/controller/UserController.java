package com.example.todowithauth.controller;

import com.example.todowithauth.dto.CustomUserDTO;
import com.example.todowithauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/")
  public List<CustomUserDTO> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/")
  public CustomUserDTO createUser(@RequestBody CustomUserDTO userDTO) {
    return userService.saveUser(userDTO);
  }
}