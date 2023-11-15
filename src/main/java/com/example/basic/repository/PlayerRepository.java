package com.example.basic.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.basic.model.Player;

public interface PlayerRepository 
     extends JpaRepository<Player, Integer>{
     
  // 회원이름으로 조회
  List<Player> findByPlayerName(
     String playerName);

  // 회원이름 Like 조회, 이름 내림차순 정렬
  List<Player> findByPlayerNameContainingOrderByPlayerNameDesc(
     String search
  );

  @Query(nativeQuery = true, 
     value = "select * from player, team"
           + " where player_name like '%' || ?1 || '%'"
           + "   and player.team_id = team.team_id"
           + " order by player_name desc")
  List<Map<String, Object>> find2(String search);
}
