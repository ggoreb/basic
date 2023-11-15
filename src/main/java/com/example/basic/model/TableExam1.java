package com.example.basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity   //(name = "table_exam_1")
@Data
public class TableExam1 {
  @Id
  @GeneratedValue
  Integer id; // Wrapper Class (기본 자료형 -> 참조 자료형)
  @Column(length = 100, nullable = true)
  String title2;
  //@Column(name = "description", length = 1000, nullable = true)
  String content;
  
   @Column(nullable = true)
  Long price;
  
  String brand;
}