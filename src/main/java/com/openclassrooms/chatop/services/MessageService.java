package com.openclassrooms.chatop.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.requests.MessageRequest;
import com.openclassrooms.chatop.models.Message;
import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.repositories.MessageRepository;
import com.openclassrooms.chatop.repositories.RentalRepository;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserService userService;

  public Optional<Message> createMessage(MessageRequest messageRequest) throws IOException {
    if (messageRequest.getRental_id() == null
    || messageRequest.getUser_id() == null
    || messageRequest.getMessage() == null) {
      return null;
    }
    
    Optional<Rental> relatedRental = rentalRepository.findById(messageRequest.getRental_id());
    if (relatedRental.isEmpty()) {
      return null;
    }
  
    if (messageRequest.getUser_id() != userService.getCurrentUser().get().getId()) {
      return null;
    }

    Message messageCreated = new Message();
    messageCreated.setRental_id(messageRequest.getRental_id());
    messageCreated.setUser_id(messageRequest.getUser_id());
    messageCreated.setMessage(messageRequest.getMessage());

    messageRepository.save(messageCreated);

    return Optional.of(messageCreated);
  }
  
}
