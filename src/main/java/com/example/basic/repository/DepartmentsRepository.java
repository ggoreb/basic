package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Departments;

@Repository
public interface DepartmentsRepository 
    extends JpaRepository<Departments, Integer> {
  
  Departments findByDepartmentName(String departmentName);

}



