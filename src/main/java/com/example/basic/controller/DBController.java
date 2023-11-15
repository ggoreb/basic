package com.example.basic.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.dao.DemoDao;
import com.example.basic.model.Major;
import com.example.basic.model.Player;
import com.example.basic.model.Point;
import com.example.basic.model.ServiceCenter;
import com.example.basic.model.TableExam1;
import com.example.basic.model.Team;
import com.example.basic.repository.MajorRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.PointRepository;
import com.example.basic.repository.ServiceCenterRepository;
import com.example.basic.repository.TableExam1Repository;
import com.example.basic.repository.TeamRepository;

@RestController
public class DBController {
  @Autowired
  DemoDao demoDao;
  @Autowired
  TableExam1Repository tableExam1Repository;

  @Autowired
  ServiceCenterRepository serviceCenterRepository;

  @Autowired
  MajorRepository majorRepository;

  @Autowired
  PointRepository pointRepository;

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  PlayerRepository playerRepository;

  @GetMapping("/team")
  public List<Team> team() {
    List<Team> list = teamRepository.findAll();
    System.out.println(list);
    return list;
  }

  @GetMapping("/player2/{name}")
  public List<Map<String, Object>> player2(
    @PathVariable String name
  ) {
    List<Map<String, Object>> list = 
      playerRepository.find2(name);
    return list;
  }

  @GetMapping("/player/{name}")
  public List<Player> player(
    @PathVariable String name
  ) {
    // List<Player> list = 
    //   playerRepository.findByPlayerName(name);
    List<Player> list = playerRepository.findByPlayerNameContainingOrderByPlayerNameDesc(name);
    return list;
  }
  @GetMapping("/player")
  public List<Player> player() {
    List<Player> list = playerRepository.findAll();
    return list;
  }

  @GetMapping("/point")
  public List<Point> point() {
    List<Point> list = pointRepository.findAll();
    return list;
  }

  @GetMapping("/major/list")
  public List<Major> majorList() {
    List<Major> list = majorRepository.findAll();
    return list;
  }

  @GetMapping("/major/add")
  public Major majorAdd(@ModelAttribute Major major) {
    // Unix Time 17억 초??? 1970년 1월 1일 0시 0분 0초 ~ 현재까지 흘러온 시간
    long time = System.currentTimeMillis(); 
    System.out.println(time);
    time = time + (1000 * 60 * 60 * 9);   // time += ??

    Date date = new Date();
    date.setTime(time); // 현재 시각
    major.setEbtbDate(date);
    Major result = majorRepository.save(major);
    return result;
  }

  @GetMapping("/sc/delete")
  @ResponseBody
  public ServiceCenter scRemove(@ModelAttribute ServiceCenter sc) {
    Optional<ServiceCenter> opt = serviceCenterRepository.findById(sc.getId());
    ServiceCenter delSc = opt.get();
    
    serviceCenterRepository.delete(sc);
    return delSc;
  }

  @GetMapping("/sc/modify")
  @ResponseBody
  public ServiceCenter scModify(@ModelAttribute ServiceCenter sc) {
    // sc.setId(1);
    ServiceCenter result = serviceCenterRepository.save(sc);
    return result;
  }

  @GetMapping("/sc/list")
  @ResponseBody
  public List<ServiceCenter> scList() {
    List<ServiceCenter> result = serviceCenterRepository.findAll();
    return result;
  }

  @GetMapping("/sc/add")
  @ResponseBody
  public ServiceCenter scAdd(@ModelAttribute ServiceCenter sc) {
    //                            생성자를 제공하지 않음
    LocalDateTime ldt = LocalDateTime.now();
    sc.setPurDate(ldt);

    Date dt = new Date();
    sc.setVstDate(dt);

    ServiceCenter result = serviceCenterRepository.save(sc);
    return result;
  }

  @GetMapping("/jpa/save")
  public TableExam1 jpaSave(
    @RequestParam String title2,
    @ModelAttribute TableExam1 t
  ) {
    // TableExam1 t = new TableExam1();
    t.setBrand("LG");
    t.setTitle2(title2);
    t.setContent("시원하다");

    TableExam1 result = tableExam1Repository.save(t);

    return result;
  }
  @GetMapping("/jpa/delete/{num}")
  public String jpaDeleteNum(
    @PathVariable int num
  ) {
    // id 숫자를 지정해서 삭제
    tableExam1Repository.deleteById(num);

    // entity 객체 생성 후 id를 입력하고 삭제
    TableExam1 tableExam1 = new TableExam1();
    tableExam1.setId(num);
    tableExam1Repository.delete(tableExam1);

    return "삭제 완료";
  }
  @GetMapping("/jpa/{num}")
  public TableExam1 jpaNum(
    @PathVariable int num
  ) {
    Optional<TableExam1> opt = tableExam1Repository.findById(num);
    TableExam1 tableExam1 = opt.get();
    return tableExam1;
  }
  @GetMapping("/jpa")
  public List<TableExam1> jpa() {
    List<TableExam1> list = tableExam1Repository.findAll();
    return list;
  }

  @GetMapping("/jdbc/demo/update")
  public String jdbcUpdate(
    @RequestParam String id,
    @RequestParam String pw,
    @RequestParam String name
  ) {
    demoDao.update(id, pw, name);
    return "완료";
  }

  @GetMapping("/jdbc/demo/add")
  public String jdbcAdd(
    @RequestParam String id
  ) {
    demoDao.insert(id);
    return "완료";
  }
  
  @GetMapping("/jdbc/demo")
  public List<Map<String, Object>> jdbcDemo() {
    return demoDao.select();
  }
}
