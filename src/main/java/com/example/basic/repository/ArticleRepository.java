package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  
}
