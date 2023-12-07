package com.openclassrooms.chatop.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.requests.MessageRequest;
import com.openclassrooms.chatop.models.Message;
import com.openclassrooms.chatop.repositories.MessageRepository;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  public Optional<Message> createMessage(MessageRequest messageRequest) throws IOException {
    Message messageCreated = new Message();
    messageCreated.setRentalId(messageRequest.getRentalId());
    messageCreated.setUserId(messageRequest.getUserId());
    messageCreated.setMessage(messageRequest.getMessage());

    messageRepository.save(messageCreated);

    return Optional.of(messageCreated);
  }
  
}
