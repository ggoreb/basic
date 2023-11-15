package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Regions;

@Repository
public interface RegionsRepository 
    extends JpaRepository<Regions, Integer> {
  
  Regions findByRegionName(String regionName);

}



