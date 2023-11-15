package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Team {
  @Id
  int teamId;
  String teamName;
  
  @OneToMany(mappedBy = "team")
  List<Player> players = new ArrayList<>();
}