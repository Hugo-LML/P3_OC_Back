package com.openclassrooms.chatop.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  
  @Autowired
  private RentalService rentalService;

  @GetMapping("")
  @ApiOperation(value = "Get all rentals")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "All rentals retrieved successfully", response = RentalsResponse.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<RentalsResponse> getAllRentals() {
    return ResponseEntity.ok(new RentalsResponse(rentalService.getAllRentals()));
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get rental by id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Rental retrieved successfully", response = RentalResponse.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<RentalResponse> getRental(@PathVariable final Integer id) {
    Optional<Rental> rental = rentalService.getRental(id);
    RentalResponse rentalResponse = new RentalResponse();
    rentalResponse.setId(rental.get().getId());
    rentalResponse.setName(rental.get().getName());
    rentalResponse.setSurface(rental.get().getSurface());
    rentalResponse.setPrice(rental.get().getPrice());
    rentalResponse.setPicture(rental.get().getPicture());
    rentalResponse.setDescription(rental.get().getDescription());
    rentalResponse.setOwner_id(rental.get().getOwner_id());
    rentalResponse.setCreated_at(rental.get().getCreated_at());
    rentalResponse.setUpdated_at(rental.get().getUpdated_at());
    if (rental.isPresent()) {
      return ResponseEntity.ok(rentalResponse);
    } else {
      return null;
    }
  }

  @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MessageResponse> createRental(@ModelAttribute RentalRequest rentalRequest) throws IOException {
    Optional<Rental> rentalCreated = rentalService.createRental(rentalRequest);
    if (rentalCreated.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Rental created !"));
    } else {
      return null;
    }
  }

  @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MessageResponse> updateRental(@PathVariable final Integer id, @ModelAttribute RentalUpdateRequest rentalUpdateRequest) throws IOException {
    Optional<Rental> rentalUpdated = rentalService.updateRental(id, rentalUpdateRequest);
    if (rentalUpdated.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    } else {
      return null;
    }
  }
}
