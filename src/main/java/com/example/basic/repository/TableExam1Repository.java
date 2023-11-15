package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.TableExam1;

@Repository
public interface TableExam1Repository 
    extends JpaRepository<TableExam1, Integer> {

  
}