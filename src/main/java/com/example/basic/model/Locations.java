package com.example.basic.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Locations {
  @Id
  int locationId;

  String streetAddress;
  String postalCode;
  String city;
  String stateProvince;
 
  @ManyToOne
  @JoinColumn(name = "country_id")
  Countries countries;

  @OneToMany(mappedBy = "locations")
  List<Departments> departments =
      new ArrayList<>();
}
