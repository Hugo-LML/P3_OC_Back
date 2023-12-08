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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  @ApiOperation(value = "Register the user")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Registered successfully", response = AuthResponse.class),
    @ApiResponse(code = 400, message = "Incorrect name, email, or password", response = String.class)
  })
  public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
    AuthResponse authResponse = authService.register(request);
    if (authResponse != null) {
      return ResponseEntity.ok(authService.register(request));
    }
    return ResponseEntity.badRequest().body("Something went wrong !");
  }

  @PostMapping("/login")
  @ApiOperation(value = "Log in the user")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Login successfully", response = AuthResponse.class),
    @ApiResponse(code = 401, message = "Incorrect name, email, or password", response = String.class)
  })
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @GetMapping("/me")
  @ApiOperation(value = "Get the user logged informations")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Identified successfully", response = UserResponse.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<UserResponse> getMe() {
    return ResponseEntity.ok(userService.getMe());
  }

}
