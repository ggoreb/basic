package com.example.basic.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Countries {
  @Id
  String countryId;

  String countryName;
 
  @ManyToOne
  @JoinColumn(name = "region_id")
  Regions regions;
}
