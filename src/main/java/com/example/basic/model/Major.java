package com.example.basic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name="major")
// setter, getter가 없어서 데이터 출력되지 않음
@Data
public class Major {
  @Id @GeneratedValue
  int id;

  @Column(length=255) 
  String name;

  // int maxPrsn;
  Integer maxPrsn;

  Date ebtbDate;

  @OneToMany(mappedBy = "major")
  @JsonIgnore
  List<Course> courses = new ArrayList<>();
}
