package com.openclassrooms.chatop.dto.responses;

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
@ApiModel(description = "Response DTO of user logged or registered")
public class AuthResponse {

  @ApiModelProperty(value = "Generated token")
  private String token;

}
