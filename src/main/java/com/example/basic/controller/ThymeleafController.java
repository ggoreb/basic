package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.Departments;
import com.example.basic.model.Employees;
import com.example.basic.model.Locations;
import com.example.basic.model.Player;
import com.example.basic.repository.DepartmentsRepository;
import com.example.basic.repository.EmployeesRepository;
import com.example.basic.repository.LocationsRepository;
import com.example.basic.repository.PlayerRepository;

@Controller
public class ThymeleafController {
  @Autowired
  PlayerRepository playerRepository;

  @Autowired
  EmployeesRepository employeesRepository;

  @Autowired
  LocationsRepository locationsRepository;

  @Autowired
  DepartmentsRepository departmentsRepository;

  @GetMapping("pagination")
  public String pagination(
      Model model, @RequestParam(defaultValue = "1") int page) {
    int startPage = (page - 1) / 10 * 10 + 1;
    int endPage = startPage + 9;
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("page", page);
    return "pagination";
  }

  @GetMapping("mode")
  public String mode(
      Model model, @RequestParam Map<String, Object> map) {
    model.addAttribute("name", map.get("name"));
    model.addAttribute("auth", map.get("auth"));
    model.addAttribute("category", map.get("category"));
    return "mode";
  }

  @GetMapping("exam2")
  public String exam2(Model model) {
    Departments department = 
        departmentsRepository.findByDepartmentName("IT");
    
    model.addAttribute("department", department);
    System.out.println(4/0);
    return "exam2";
  }

  @GetMapping("exam1")
  public String exam1(Model model) {
    Locations location = 
      locationsRepository.findByStateProvince(
        "Washington");
    model.addAttribute("location", location);
    return "exam1";
  }

  @GetMapping("empList")
  public String empList(Model model) {
    List<Employees> list = employeesRepository.findAll();
    model.addAttribute("list", list);
    return "empList";
  }

  @GetMapping("userList")
  public String userList(Model model) {
    List<Player> list = playerRepository.findAll();
    model.addAttribute("list", list);

    List<Map<String, Object>> userList = new ArrayList<>();
    Map<String, Object> user = null;
    user = new HashMap<>();
    user.put("userId", "a");
    user.put("userName", "apple");
    user.put("userAge", 21);
    userList.add(user);
    user = new HashMap<>();
    user.put("userId", "b");
    user.put("userName", "banana");
    user.put("userAge", 22);
    userList.add(user);
    user = new HashMap<>();
    user.put("userId", "c");
    user.put("userName", "carrot");
    user.put("userAge", 23);
    userList.add(user);
    model.addAttribute("userList", userList);
    return "userList";
  }


  @GetMapping("user")
  public String user(Model model) {
    Map<String, Object> user = null;
    user = new HashMap<>();
    user.put("userId", "z");
    user.put("userName", "zoo");
    user.put("userAge", 25);
    model.addAttribute("user", user);
    return "user";
  }
}
