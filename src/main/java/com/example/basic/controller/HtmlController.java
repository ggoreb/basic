package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.basic.model.Member;

@Controller
public class HtmlController {
  @GetMapping("/html/exam")
  public String htmlExam() {
    return "html/exam";
  }

  @GetMapping("html/object")
  public Member htmlObject() {
    Member member = new Member();
    member.setName("kim");
    return member;
  }

  @GetMapping("/html/string")
  public String htmlString() {
    return "html/string";
  }
}
