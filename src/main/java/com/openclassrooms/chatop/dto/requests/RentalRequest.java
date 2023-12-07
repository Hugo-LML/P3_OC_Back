package com.openclassrooms.chatop.dto.requests;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class RentalRequest {
  
  @NonNull
  private String name;

  @NonNull
  private Integer surface;

  @NonNull
  private Integer price;

  @NonNull
  private String picture;

  @NonNull
  private String description;

}
