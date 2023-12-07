package com.openclassrooms.chatop.dto.requests;

import lombok.Data;

@Data
public class RentalUpdateRequest {
  
  private String name;

  private Integer surface;

  private Integer price;

  private String description;

}
