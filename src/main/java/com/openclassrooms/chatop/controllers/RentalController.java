package com.openclassrooms.chatop.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Object> getAllRentals() {
    Iterable<Rental> getAllRentalsResponse = rentalService.getAllRentals();
    if (getAllRentalsResponse != null) {
      return ResponseEntity.ok(new RentalsResponse(getAllRentalsResponse));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get rental by id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Rental retrieved successfully", response = RentalResponse.class),
    @ApiResponse(code = 400, message = "Incorrect rental_id", response = String.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<Object> getRental(@PathVariable final Integer id) {
    Rental rental = rentalService.getRental(id);
    if (rental != null) {
      RentalResponse rentalResponse = new RentalResponse();
      rentalResponse.setId(rental.getId());
      rentalResponse.setName(rental.getName());
      rentalResponse.setSurface(rental.getSurface());
      rentalResponse.setPrice(rental.getPrice());
      rentalResponse.setPicture(rental.getPicture());
      rentalResponse.setDescription(rental.getDescription());
      rentalResponse.setOwner_id(rental.getOwner_id());
      rentalResponse.setCreated_at(rental.getCreated_at());
      rentalResponse.setUpdated_at(rental.getUpdated_at());
      return ResponseEntity.ok(rentalResponse);
    }
    return ResponseEntity.badRequest().body("Incorrect rental_id or token");
  }

  @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ApiOperation(value = "Create rental")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Rental created successfully", response = MessageResponse.class),
    @ApiResponse(code = 400, message = "Incorrect body request", response = String.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<Object> createRental(@ModelAttribute RentalRequest rentalRequest) throws IOException {
    Optional<Rental> rentalCreated = rentalService.createRental(rentalRequest);
    if (rentalCreated.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }
    return ResponseEntity.badRequest().body("Incorrect rental body request or token");
  }

  @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ApiOperation(value = "Update rental")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Rental updated successfully", response = MessageResponse.class),
    @ApiResponse(code = 400, message = "Incorrect body request", response = String.class),
    @ApiResponse(code = 401, message = "Incorrect token", response = String.class)
  })
  public ResponseEntity<Object> updateRental(@PathVariable final Integer id, @ModelAttribute RentalUpdateRequest rentalUpdateRequest) throws IOException {
    Optional<Rental> rentalUpdated = rentalService.updateRental(id, rentalUpdateRequest);
    if (rentalUpdated != null) {
      return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    }
    return ResponseEntity.badRequest().body("Incorrect rental body request or token");
  }
}
