package com.openclassrooms.chatop.dto.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Request DTO to update a rental")

public class RentalUpdateRequest {
  
  @ApiModelProperty(value = "Name of the updated rental")
  private String name;
  
  @ApiModelProperty(value = "Surface of the updated rental")
  private Integer surface;
  
  @ApiModelProperty(value = "Price of the updated rental")
  private Integer price;
  
  @ApiModelProperty(value = "Description of the updated rental")
  private String description;

}
