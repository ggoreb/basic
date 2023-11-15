package com.example.basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NightPharmacy {
  @Id
  Integer id;
  String name;
  String tel;
  String roadAddress;
  String jibunAddress;
  String mon;
  String tue;
  String wed;
  String thu;
  String fri;
  String sat;
  String sun;
  String pubDay;
}
