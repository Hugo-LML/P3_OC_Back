package com.openclassrooms.chatop.dto.responses;

import com.openclassrooms.chatop.models.Rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalsResponse {
  
  private Iterable<Rental> rentals;

}
