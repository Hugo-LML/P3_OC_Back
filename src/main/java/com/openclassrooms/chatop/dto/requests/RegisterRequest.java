package com.openclassrooms.chatop.dto.requests;

import org.springframework.lang.NonNull;
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
@ApiModel(description = "Request DTO to register")
public class RegisterRequest {
  
  @ApiModelProperty(value = "Name of the user", required = true)
  @NonNull
  private String name;

  @ApiModelProperty(value = "Email of the user", required = true)
  @NonNull
  private String email;

  @ApiModelProperty(value = "Password of the user", required = true)
  @NonNull
  private String password;

}
