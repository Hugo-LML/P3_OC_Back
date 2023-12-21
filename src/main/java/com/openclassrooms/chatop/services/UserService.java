package com.openclassrooms.chatop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.responses.UserResponse;
import com.openclassrooms.chatop.models.User;
import com.openclassrooms.chatop.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Optional<User> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    return null;
  }

  public UserResponse getUser(final Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      UserResponse userResponse = new UserResponse();
      userResponse.setId(user.getId());
      userResponse.setName(user.getName());
      userResponse.setEmail(user.getEmail());
      userResponse.setCreated_at(user.getCreated_at());
      userResponse.setUpdated_at(user.getUpdated_at());
      return userResponse;
    }
    return null;
  }

  public UserResponse getMe() {
    Optional<User> user = getCurrentUser();
    if (user != null) {
      UserResponse userResponse = new UserResponse();
      userResponse.setId(user.get().getId());
      userResponse.setName(user.get().getName());
      userResponse.setEmail(user.get().getEmail());
      userResponse.setCreated_at(user.get().getCreated_at());
      userResponse.setUpdated_at(user.get().getUpdated_at());
      return userResponse;
    };
    return null;
  }

}
