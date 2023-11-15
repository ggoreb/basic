package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.model.Join;

public interface JoinRepository 
  extends JpaRepository<Join, String> {
  
}
