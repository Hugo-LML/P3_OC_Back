package com.openclassrooms.chatop.dto.requests;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Request DTO to create a rental")
public class RentalRequest {
  
  @ApiModelProperty(value = "Name of the created rental", required = true)
  private String name;

  @ApiModelProperty(value = "Surface of the created rental", required = true, example = "1")
  private Integer surface;

  @ApiModelProperty(value = "Price of the created rental", required = true, example = "1")
  private Integer price;

  @ApiModelProperty(value = "Picture of the created rental", required = true)
  private MultipartFile picture;

  @ApiModelProperty(value = "Description of the created rental", required = true)
  private String description;

}
