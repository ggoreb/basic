package com.example.basic.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "table_exam_2")
public class TableExam2 {
  @Id
  Integer id;

  String name;

  @Lob
  String content;

  @Temporal(TemporalType.DATE)
  Date birthDay;

  @Temporal(TemporalType.TIME)
  Date birthTime;

  @Column(name = "sign_up_date")
  @Temporal(TemporalType.TIMESTAMP)
  Date signupDate;
}