package com.example.todowithauth.service;

import com.example.todowithauth.dto.CustomUserDTO;
import com.example.todowithauth.model.CustomUser;
import com.example.todowithauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomUser user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur: " + username);
    }
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(/* Ajoute les rôles de l'utilisateur ici si nécessaire */)
        .build();
  }

  public List<CustomUserDTO> getAllUsers() {
    List<CustomUser> users = userRepository.findAll();
    return users.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public CustomUserDTO saveUser(CustomUserDTO userDTO) {
    CustomUser user = convertToEntity(userDTO);
    CustomUser savedUser = userRepository.save(user);
    return convertToDTO(savedUser);
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
}