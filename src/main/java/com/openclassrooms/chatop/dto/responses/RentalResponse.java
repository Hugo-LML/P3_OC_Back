package com.openclassrooms.chatop.dto.responses;

import java.util.Date;

// import com.openclassrooms.chatop.models.Rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {

  // private Rental rental;

  private Integer id;

  private String name;

  private Integer surface;

  private Integer price;

  private String picture;

  private String description;

  private Integer owner_id;

  private Date createdAt;

  private Date updatedAt;


}
