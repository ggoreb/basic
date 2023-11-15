package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Member;

@Controller
public class Json1Controller {
  @GetMapping("json/exam")
  @ResponseBody
  public Map<String, Object> jsonExam() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("count", 2);

    List<Map<String, String>> list = new ArrayList<>();
    Map<String, String> map2 = new HashMap<>();
    map2.put("name", "가");
    list.add(map2);

    Map<String, String> map3 = new HashMap<>();
    map3.put("name", "나");
    list.add(map3);

    map.put("list", list);
    return map;
  }

  @GetMapping("json1/string")
  @ResponseBody
  public String json() {
    return "json/string";
  }

  @GetMapping("json1/map")
  @ResponseBody
  public Map<String, Object> jsonMap(Map<String, Object> map) {
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("key1", "value");
    map2.put("key2", 2324);
    map2.put("key3", true);
    return map2;
  }

  @GetMapping("json1/object")
  @ResponseBody
  public Member jsonObject() {
    Member member = new Member();
    member.setName("kim");
    return member;
  }

  @GetMapping("json1/list")
  @ResponseBody
  public List<String> jsonList() {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    return list;
  }
}
