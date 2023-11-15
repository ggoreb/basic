package com.example.basic.model;

import org.hibernate.id.IntegralDataTypeHolder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity @Data
public class Point {
  @Id
  Integer id;

  String title;

  Double lat;
  Double lng;
}
