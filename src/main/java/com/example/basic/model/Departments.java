package com.example.basic.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"locations", "employees"})
public class Departments {
  @Id
  int departmentId;

  String departmentName;

  @ManyToOne
  @JoinColumn(name = "location_id")
  Locations locations;

  // @OneToMany(mappedBy = "departments", fetch=FetchType.EAGER)
  @OneToMany(mappedBy = "departments")
  List<Employees> employees = 
      new ArrayList<>();
}
