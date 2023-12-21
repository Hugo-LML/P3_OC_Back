package com.openclassrooms.chatop.dto.requests;

import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Request DTO to create a message")
public class MessageRequest {

  @ApiModelProperty(value = "Id of the rental targeted by the message", required = true, example = "1")
  @NonNull
  private Integer rental_id;

  @ApiModelProperty(value = "Id of the user sending the message", required = true, example = "1")
  @NonNull
  private Integer user_id;

  @ApiModelProperty(value = "Content of the message", required = true)  
  @NonNull
  private String message;

}
