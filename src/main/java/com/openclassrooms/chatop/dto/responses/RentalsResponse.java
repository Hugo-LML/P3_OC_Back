package com.openclassrooms.chatop.dto.responses;

import com.openclassrooms.chatop.models.Rental;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Response DTO of retrieved rentals")
public class RentalsResponse {
  
  @ApiModelProperty(value = "List of retrieved rentals")
  private Iterable<Rental> rentals;

}
