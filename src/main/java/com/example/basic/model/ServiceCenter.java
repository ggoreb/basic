package com.example.basic.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ServiceCenter {
  @Id
  @GeneratedValue
  Integer id;
  
  String customer;
  String prdName;
  LocalDateTime purDate;
  Date vstDate;
}