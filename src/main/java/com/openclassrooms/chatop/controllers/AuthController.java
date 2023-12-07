package com.openclassrooms.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.requests.LoginRequest;
import com.openclassrooms.chatop.dto.requests.RegisterRequest;
import com.openclassrooms.chatop.dto.responses.AuthResponse;
import com.openclassrooms.chatop.dto.responses.UserResponse;
import com.openclassrooms.chatop.services.AuthService;
import com.openclassrooms.chatop.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getMe() {
    return ResponseEntity.ok(userService.getMe());
  }

}
