package com.openclassrooms.chatop.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.requests.LoginRequest;
import com.openclassrooms.chatop.dto.requests.RegisterRequest;
import com.openclassrooms.chatop.dto.responses.AuthResponse;
import com.openclassrooms.chatop.models.User;
import com.openclassrooms.chatop.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse register(RegisterRequest request) {
    if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
      return null;
    }
    var user = User.builder()
      .name(request.getName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .build();
    
    repository.save(user);
    
    var jwtToken = jwtService.createToken(user);
    return AuthResponse.builder()
      .token(jwtToken)
      .build();
  }

  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword())
    );
    var user = repository.findByEmail(request.getEmail())
      .orElseThrow();
    
    var jwtToken = jwtService.createToken(user);
    return AuthResponse.builder()
      .token(jwtToken)
      .build();
  }

}
