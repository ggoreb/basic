package com.example.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RequestController {
  @GetMapping("req/data")
  public Map<String, String> data(
    @RequestParam(required = false) String area,
    @RequestParam(required = false) String score,
    @RequestParam Map<String, String> m) {

    // Map<String, String> map = new HashMap<>();
    // if(area != null) {
    //   map.put("area", area);
    // }
    // if(score != null) {
    //   map.put("score", score);
    // }

    return m;
  }

  @GetMapping("req/http")
  public String http(HttpServletRequest request) {
    String name = request.getParameter("name");
    int pageNum = 
      Integer.parseInt(request.getParameter("pageNum"));
    
    return name + ", " + pageNum;
  }
}