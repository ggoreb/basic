package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MntalkController {
  
  @GetMapping("/mntalk")
  public String mntalk() {
    return "mntalk/index";
  }

  @GetMapping("/mntalk/read")
  public String read() {
    return "mntalk/read";
  }


}
