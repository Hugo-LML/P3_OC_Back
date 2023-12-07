package com.openclassrooms.chatop.dto.requests;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class MessageRequest {

  @NonNull
  private Integer rentalId;

  @NonNull
  private Integer userId;
  
  @NonNull
  private String message;

}
