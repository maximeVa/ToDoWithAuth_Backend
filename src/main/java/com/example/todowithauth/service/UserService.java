package com.example.todowithauth.service;

import com.example.todowithauth.dto.CustomUserDTO;
import com.example.todowithauth.model.CustomUser;
import com.example.todowithauth.repository.UserRepository;
import com.example.todowithauth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;


  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomUser user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur: " + username);
    }
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(user.getRoles())
        .build();
  }

  public List<CustomUserDTO> getAllUsers() {
    List<CustomUser> users = userRepository.findAll();
    return users.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public boolean saveUser(CustomUserDTO userDTO) {
    // Hash the password
    String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

    // Create a new CustomUser entity from DTO
    CustomUser newUser = new CustomUser(userDTO.getUsername(), userDTO.getEmail(), hashedPassword);

    // Call UserRepository method to save the user
    try {
      userRepository.save(newUser);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // Méthode pour convertir une entité User en DTO
  private CustomUserDTO convertToDTO(CustomUser user) {
    CustomUserDTO userDTO = new CustomUserDTO();
    userDTO.setId(user.getId());
    userDTO.setUsername(user.getUsername());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }

  // Méthode pour convertir un DTO en entité User
  private CustomUser convertToEntity(CustomUserDTO userDTO) {
    CustomUser user = new CustomUser();
    user.setId(userDTO.getId());
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    return user;
  }

  public String generateToken(CustomUser user) {
    return jwtTokenUtil.generateAccessToken(user);
  }
}