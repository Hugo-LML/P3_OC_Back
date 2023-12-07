package com.openclassrooms.chatop.services;

import java.io.IOException;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.requests.RentalRequest;
import com.openclassrooms.chatop.dto.requests.RentalUpdateRequest;
import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.repositories.RentalRepository;

@Service
public class RentalService {
  
  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserService userService;

  public Iterable<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public Optional<Rental> getRental(final Integer id) {
    return rentalRepository.findById(id);
  }

  public Optional<Rental> createRental(RentalRequest rentalRequest) throws IOException {
    return userService.getCurrentUser().map(currentUser -> {
        Rental rentalCreated = new Rental();
        rentalCreated.setName(rentalRequest.getName());
        rentalCreated.setSurface(rentalRequest.getSurface());
        rentalCreated.setPrice(rentalRequest.getPrice());
        rentalCreated.setPicture(rentalRequest.getPicture());
        rentalCreated.setDescription(rentalRequest.getDescription());
        rentalCreated.setOwner_id(currentUser.getId());

        return rentalRepository.save(rentalCreated);
    });
  }

  public Optional<Rental> updateRental(Integer id, RentalUpdateRequest rentalUpdateRequest) throws IOException {
    Optional<Rental> rental = getRental(id);
    if (rental.isPresent()) {
      Rental currentRental = rental.get();

      String name = rentalUpdateRequest.getName();
      if (name != null) {
        currentRental.setName(name);
      }

      Integer surface = rentalUpdateRequest.getSurface();
      if (surface != null) {
        currentRental.setSurface(surface);
      }

      Integer price = rentalUpdateRequest.getPrice();
      if (price != null) {
        currentRental.setPrice(price);
      }

      String description = rentalUpdateRequest.getDescription();
      if (description != null) {
        currentRental.setDescription(description);
      }

      rentalRepository.save(currentRental);
      return Optional.of(currentRental);
    } else {
      return null;
    }
  }

}
