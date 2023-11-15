package com.example.basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Join {
  @Id
  String id;

  @Column(length=100)
  String pw;

  String name;
  String joinDate;
}
