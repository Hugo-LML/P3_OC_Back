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
@ApiModel(description = "Response DTO of retrieved rental")
public class RentalResponse {

  @ApiModelProperty(value = "Id of the retrieved rental")
  private Integer id;

  @ApiModelProperty(value = "Name of the retrieved rental")
  private String name;

  @ApiModelProperty(value = "Surface of the retrieved rental")
  private Integer surface;

  @ApiModelProperty(value = "Price of the retrieved rental")
  private Integer price;

  @ApiModelProperty(value = "Picture of the retrieved rental")
  private String picture;

  @ApiModelProperty(value = "Description of the retrieved rental")
  private String description;

  @ApiModelProperty(value = "Owner id of the retrieved rental")
  private Integer owner_id;

  @ApiModelProperty(value = "Cration date of the retrieved rental")
  private Date created_at;

  @ApiModelProperty(value = "Last update date of the retrieved rental")
  private Date updated_at;

}
