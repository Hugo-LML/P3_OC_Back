package com.openclassrooms.chatop.dto.responses;

import java.util.Date;

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
@ApiModel(description = "Response DTO of retrieved user")
public class UserResponse {
  
  @ApiModelProperty(value = "Id of the retrieved user")
  private Integer id;

  @ApiModelProperty(value = "Name of the retrieved user")
  private String name;

  @ApiModelProperty(value = "Email of the retrieved user")
  private String email;

  @ApiModelProperty(value = "Creation date of the retrieved user")
  private Date created_at;

  @ApiModelProperty(value = "Last update date of the retrieved user")
  private Date updated_at;

}
