package com.example.basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Article {
  @Id @GeneratedValue
  int id;
  String title;
  String fileInfo;
}
