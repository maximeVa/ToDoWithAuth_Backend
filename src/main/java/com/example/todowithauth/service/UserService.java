package com.example.todowithauth.service;

import com.example.todowithauth.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.example.todowithauth.model.User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur: " + username);
    }
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(/* Ajoute les rôles de l'utilisateur ici si nécessaire */)
        .build();
  }

  public List<com.example.todowithauth.model.User> getAllUsers() {
    return userRepository.findAll();
  }

  public com.example.todowithauth.model.User saveUser(com.example.todowithauth.model.User user) {
    return userRepository.save(user);
  }
}