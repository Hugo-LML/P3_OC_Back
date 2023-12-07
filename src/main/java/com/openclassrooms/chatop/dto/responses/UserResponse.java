package com.openclassrooms.chatop.dto.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  
  private Integer id;

  private String name;

  private String email;

  private Date createdAt;

  private Date updatedAt;

}
