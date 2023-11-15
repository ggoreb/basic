package com.example.basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Course {
  @Id @GeneratedValue
  int id;

  @Column(nullable=false)
  String name;
  
  @ManyToOne
  Major major; // major_id
}
