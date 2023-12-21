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

  @Autowired
  private UploadService uploadService;

  public Iterable<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public Rental getRental(final Integer id) {
    return rentalRepository.findById(id).orElse(null);
  }

  public Optional<Rental> createRental(RentalRequest rentalRequest) throws IOException {
    return userService.getCurrentUser().map(currentUser -> {
      try {
        String fileName = uploadService.storeFile(rentalRequest.getPicture());
        if (
          rentalRequest.getName() == null || rentalRequest.getName().isEmpty()
          || rentalRequest.getSurface() <= 0 || rentalRequest.getSurface() == null
          || rentalRequest.getPrice() <= 0 || rentalRequest.getPrice() == null
          || rentalRequest.getDescription() == null || rentalRequest.getDescription().isEmpty()
          || fileName == null
        ) {
          return null;
        }
        Rental rentalCreated = new Rental();
        rentalCreated.setName(rentalRequest.getName());
        rentalCreated.setSurface(rentalRequest.getSurface());
        rentalCreated.setPrice(rentalRequest.getPrice());
        rentalCreated.setPicture(fileName);
        rentalCreated.setDescription(rentalRequest.getDescription());
        rentalCreated.setOwner_id(currentUser.getId());
  
        return rentalRepository.save(rentalCreated);
      } catch (Exception e) {
        return null;
      }
    });
  }

  public Optional<Rental> updateRental(Integer id, RentalUpdateRequest rentalUpdateRequest) throws IOException {
    Rental rental = getRental(id);
    if (rental != null) {

      String name = rentalUpdateRequest.getName();
      if (name != null && !name.isEmpty()) {
        rental.setName(name);
      }

      Integer surface = rentalUpdateRequest.getSurface();
      if (surface != null && surface >= 0) {
        rental.setSurface(surface);
      }

      Integer price = rentalUpdateRequest.getPrice();
      if (price != null && surface >= 0) {
        rental.setPrice(price);
      }

      String description = rentalUpdateRequest.getDescription();
      if (description != null && !description.isEmpty()) {
        rental.setDescription(description);
      }

      rentalRepository.save(rental);
      return Optional.of(rental);
    } else {
      return null;
    }
  }

}
