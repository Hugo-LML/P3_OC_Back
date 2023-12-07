package com.openclassrooms.chatop.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.requests.RentalRequest;
import com.openclassrooms.chatop.dto.requests.RentalUpdateRequest;
import com.openclassrooms.chatop.dto.responses.MessageResponse;
import com.openclassrooms.chatop.dto.responses.RentalResponse;
import com.openclassrooms.chatop.dto.responses.RentalsResponse;
import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.services.RentalService;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  
  @Autowired
  private RentalService rentalService;

  @GetMapping("")
  public ResponseEntity<RentalsResponse> getAllRentals() {
    return ResponseEntity.ok(new RentalsResponse(rentalService.getAllRentals()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalResponse> getRental(@PathVariable final Integer id) {
    Optional<Rental> rental = rentalService.getRental(id);
    if (rental.isPresent()) {
      return ResponseEntity.ok(new RentalResponse(rental.get()));
    } else {
      return null;
    }
  }

  @PostMapping("")
  public ResponseEntity<MessageResponse> createRental(@RequestBody RentalRequest rentalRequest) throws IOException {
    Optional<Rental> rentalCreated = rentalService.createRental(rentalRequest);
    if (rentalCreated.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Rental created !"));
    } else {
      return null;
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<MessageResponse> updateRental(@PathVariable final Integer id, @RequestBody RentalUpdateRequest rentalUpdateRequest) throws IOException {
    Optional<Rental> rentalUpdated = rentalService.updateRental(id, rentalUpdateRequest);
    if (rentalUpdated.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    } else {
      return null;
    }
  }
}
