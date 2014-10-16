package com.example.footballclub.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.footballclub.domain.Player;
import com.example.footballclub.service.PlayerManager;

public class PlayerManagerTest {
	
	
	PlayerManager playerManager = new PlayerManager();
	private final static String NAME_1 = "Grzegorz";
	private final static String LASTNAME_1 = "Niciński";
	private final static String POSITION_1 = "Striker";
	private final static int AGE_1 = 35;
	
	private final static String NAME_2 = "Krzysztof";
	private final static String LASTNAME_2 = "Sobieraj";
	private final static String POSITION_2 = "Defender";	
	private final static int AGE_2 = 31;
	
	
	@Test
	public void checkConnection(){
		assertNotNull(playerManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1);
		
		playerManager.clearPlayers();
		assertEquals(1,playerManager.addPlayer(player));
		
		List<Player> players = playerManager.getAllPlayers();
		Player personRetrieved = players.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(LASTNAME_1, personRetrieved.getLastName());
		assertEquals(POSITION_1, personRetrieved.getPosition());
		assertEquals(AGE_1, personRetrieved.getAge());
		
	}
	
	@Test
	public void checkDeleting() {
		playerManager.clearPlayers();
		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1);
		assertEquals(1,playerManager.addPlayer(player));
		
		
		List<Player> players = playerManager.getAllPlayers();
		Player pr = players.get(0);
		
		assertSame(1,playerManager.deletePlayer(pr));	
	}
	
	@Test
	public void checkAllDeleting() {
		playerManager.clearPlayers();
		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1);
		assertEquals(1,playerManager.addPlayer(player));
		assertEquals(1,playerManager.addPlayer(player));
		assertEquals(1,playerManager.addPlayer(player));
		
		Player player2 = new Player(NAME_2, LASTNAME_2, POSITION_2, AGE_2);
		assertEquals(1,playerManager.addPlayer(player2));
		assertEquals(1,playerManager.addPlayer(player2));
		assertEquals(1,playerManager.addPlayer(player2));
		
		List<Player> players = playerManager.getAllPlayers();
		
		assertSame(6, players.size());
		
		playerManager.clearPlayers();
		
		players = playerManager.getAllPlayers();
		
		assertSame(0, players.size());
	}
	
	@Test
	public void checkUpdate() {
				
		playerManager.clearPlayers();
		
		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1);
		
		assertEquals(1,playerManager.addPlayer(player));
		
		List<Player> players = playerManager.getAllPlayers();
		Player pr = players.get(0);
		
		assertEquals(POSITION_1, pr.getPosition());
		
		assertEquals(1, playerManager.updatePlayer(pr, POSITION_2));
		
		players = playerManager.getAllPlayers();
		Player pr2 = players.get(0);
		
		assertEquals(POSITION_2, pr2.getPosition());

		
	}
	
	@Test
	public void checkSelectOne() {
		playerManager.clearPlayers();
		
		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1);
		
		assertEquals(1,playerManager.addPlayer(player));
		
		List<Player> players = playerManager.getAllPlayers();
		Player pr = players.get(0);
		
		long id = pr.getId();
		
		assertNotNull(playerManager.getPlayer(id));
		
		Player personRetrieved = playerManager.getPlayer(id);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(LASTNAME_1, personRetrieved.getLastName());
		assertEquals(POSITION_1, personRetrieved.getPosition());
		assertEquals(AGE_1, personRetrieved.getAge());

	}

}
