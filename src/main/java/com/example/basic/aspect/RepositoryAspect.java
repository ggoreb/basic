package com.example.basic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Aspect
// @Component
public class RepositoryAspect {
  @AfterReturning(
    value = "execution (* com.example.basic.repository.*.find*(..))", 
    returning = "data")
  public void onAfterReturningHandler(JoinPoint joinPoint, Object data) {
    if (data != null) {
      log.debug(data.toString());
    }
    log.debug("Repository @AfterReturning run");
  }
}
