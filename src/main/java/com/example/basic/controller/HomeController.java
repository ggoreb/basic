package com.example.basic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.basic.vo.DataVO;

@Controller
public class HomeController {
  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/news")
  @ResponseBody
  public Map<String, Object> news() {
    String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=9f5baf7d9f3f42879a20d7d19d9886e4";
    return restTemplate.getForObject(url, Map.class);


    // RestTemplate rt = new RestTemplate();
    // String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=9f5baf7d9f3f42879a20d7d19d9886e4";

    // return rt.getForObject(url, Map.class);

    // Map<String, Object> map = rt.getForObject(url, Map.class);
    // return map;
  }

  @GetMapping("/rp/{number}")
  @ResponseBody // HTML 템플릿 없이 응답
  public String rp2(@PathVariable String number) {
    return number;
  }

  @GetMapping("/rp")
  @ResponseBody // HTML 템플릿 없이 응답
  public DataVO rp(
    @RequestParam String userId,
    @RequestParam String userName,
    @ModelAttribute DataVO vo
  ) {
    // return userId + ", " + userName + 
    //        ", " + vo.toString();
    return vo;
  }

  @GetMapping("/")
  public String home() {
    return "home"; 
  }
}
