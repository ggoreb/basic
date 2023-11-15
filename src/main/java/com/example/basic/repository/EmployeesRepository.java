package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.model.Employees;

public interface EmployeesRepository 
  extends JpaRepository<Employees, Integer> {

}
