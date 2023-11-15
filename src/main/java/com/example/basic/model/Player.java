package com.example.basic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "team")
public class Player {
  @Id
  int playerId;
  String playerName;
  // team + teamId
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="team_id")
  Team team;
}