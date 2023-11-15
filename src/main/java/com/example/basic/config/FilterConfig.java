package com.example.basic.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.basic.filter.IPCheckFilter;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<Filter> getFilterRegistrationBean() {
    FilterRegistrationBean<Filter> bean = 
        new FilterRegistrationBean<>(new IPCheckFilter());
    bean.addUrlPatterns("/*");
    return bean;
  }
}
