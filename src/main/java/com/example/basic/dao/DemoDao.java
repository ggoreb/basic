package com.example.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// ComponentScan에 의해 Bean으로 등록
@Repository 
public class DemoDao {
  // 스프링의 컨테이너에 생성되어 있는
  // Bean 중에 1개를 연결
  @Autowired 
  JdbcTemplate jt;

  public void update(
      String id, String pw, String name) {
    String sql = "update join set " +
                 "  pw=?, name=?" +
                 "  where id=?";
    jt.update(sql, pw, name, id);
  }

  public void insert(String id) {
    jt.update(
      "insert into join values " +
      "  (?, null, null, null)", id
    );
  }

  public List<Map<String, Object>> select() {
    return jt.queryForList("select * from join");
  }
}

