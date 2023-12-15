package com.openclassrooms.chatop.models;

import java.util.Date;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Integer surface;

  private Integer price;

  private String picture;

  private String description;

  private Integer owner_id;

  private Date created_at;

  private Date updated_at;

  @PrePersist
  protected void onCreate() {
    created_at = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updated_at = new Date();
  }

}
