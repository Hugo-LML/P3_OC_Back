package com.openclassrooms.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    AuthResponse registerResponse = authService.register(request);
    if (registerResponse != null) {
      return ResponseEntity.ok(registerResponse);
    }
    return ResponseEntity.badRequest().body("Incorrect name, email, or password");
  }

  @PostMapping("/login")
  @ApiOperation(value = "Log in the user")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Login successfully", response = AuthResponse.class),
    @ApiResponse(code = 401, message = "Incorrect name, email, or password", response = String.class)
  })
  public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
    AuthResponse loginResponse = authService.login(request);
    if (loginResponse != null) {
      return ResponseEntity.ok(loginResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect name, email, or password");
  }

  @GetMapping("/me")
  @ApiOperation(value = "Get the user logged informations")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Identified successfully", response = UserResponse.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<Object> getMe() {
    UserResponse getMeResponse = userService.getMe();
    if (getMeResponse != null) {
      return ResponseEntity.ok(getMeResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

}
