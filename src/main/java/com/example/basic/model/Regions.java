package com.example.basic.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Regions {
  @Id
  int regionId;

  String regionName;
 
  @OneToMany(mappedBy = "regions")
  List<Countries> countries = new ArrayList<>();
}
