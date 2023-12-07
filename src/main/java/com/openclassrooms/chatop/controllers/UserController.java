package com.openclassrooms.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.responses.UserResponse;
import com.openclassrooms.chatop.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable final Integer id) {
    UserResponse user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

}
