package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Point;

@Repository
public interface PointRepository 
   extends JpaRepository<Point, Integer> {
  
}
