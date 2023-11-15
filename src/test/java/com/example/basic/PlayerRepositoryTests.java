package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.model.Player;
import com.example.basic.model.Team;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.TeamRepository;

@SpringBootTest
class PlayerRepositoryTests {
	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	TeamRepository teamRepository;

	@Test
	void 회원정보입력_외래키처리3() {
		Player player = new Player();
		player.setPlayerId(9);
		player.setPlayerName("나길동");

		// Team의 객체에 PK를 직접 입력
		Team team = new Team();
		team.setTeamId(4);
		team.setTeamName("KT");
		teamRepository.save(team);

		player.setTeam(team);
		
		playerRepository.save( player );
	}
	@Test
	void 회원정보입력_외래키처리2() {
		Player player = new Player();
		player.setPlayerId(8);
		player.setPlayerName("이길동");

		// Team의 객체에 PK를 직접 입력
		Team team = new Team();
		team.setTeamId(1);
		player.setTeam(team);
		
		playerRepository.save( player );
	}
	@Test
	void 회원정보입력_외래키처리() {
		Player player = new Player();
		player.setPlayerId(7);
		player.setPlayerName("박길동");

		// Team의 객체 DB로부터 가져오기
		Optional<Team> opt = teamRepository.findById(2); // B팀
    Team team = opt.get();
		player.setTeam(team);

		playerRepository.save( player );
	}

	@Test
	void 팀입력테스트_중복값입력() {
		Team team = new Team();
		team.setTeamId(1);
		team.setTeamName("LG");
		teamRepository.save(team); // PK(id)가 중복되면 데이터가 수정됨
	}

	@Test
	void contextLoads() {
		String s = "회원";
		List<Player> list = playerRepository.findByPlayerNameContainingOrderByPlayerNameDesc(s);
		System.out.println(list);
	}

}
