package com.openclassrooms.chatop.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.requests.MessageRequest;
import com.openclassrooms.chatop.dto.responses.MessageResponse;
import com.openclassrooms.chatop.models.Message;
import com.openclassrooms.chatop.services.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  private MessageService messageService;
  
  @PostMapping("")
  public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest messageRequest) throws IOException {
    Optional<Message> messageCreated = messageService.createMessage(messageRequest);
    if (messageCreated.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Message send with success"));
    } else {
      return null;
    }
  }

}
