package com.example.basic.vo;

import lombok.Data;

@Data // Setter, Getter, toString
public class DataVO {
  String userId;
  String userName;

  // public void setUserId(String userId) {
  //   this.userId = userId;
  // }
  // public void setUserName(String userName) {
  //   this.userName = userName;
  // }

  // @Override
  // public String toString() {
  //   return "userId: " + userId 
  //          + ", userName: " + userName;
  // }
}
