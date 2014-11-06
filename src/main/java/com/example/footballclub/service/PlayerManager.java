package com.example.footballclub.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.footballclub.domain.Player;

public class PlayerManager {

	private Connection connection;
	
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTablePlayer = "CREATE TABLE PLAYER(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20), lastName varchar(35), position varchar(20), age integer, club_id bigint, FOREIGN KEY (club_id) REFERENCES CLUB(id) ON DELETE CASCADE)";

	private PreparedStatement addPlayerStmt;
	private PreparedStatement deletePlayerStmt;
	private PreparedStatement deleteAllPlayersStmt;
	private PreparedStatement getPlayerStmt;
	private PreparedStatement getAllPlayersStmt;
	private PreparedStatement updatePlayerStmt;

	private Statement statement;

	public PlayerManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("PLAYER".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTablePlayer);

			addPlayerStmt = connection
					.prepareStatement("INSERT INTO PLAYER (name, lastName, position, age, club_id) VALUES (?, ?, ?, ?, ?)");
			deleteAllPlayersStmt = connection
					.prepareStatement("DELETE FROM PLAYER");
			deletePlayerStmt = connection
					.prepareStatement("DELETE FROM PLAYER WHERE id = ?");
			getPlayerStmt = connection
					.prepareStatement("SELECT id, name, lastName, position, age, club_id FROM PLAYER WHERE id = ?");
			getAllPlayersStmt = connection
					.prepareStatement("SELECT id, name, lastName, position, age, club_id FROM PLAYER");
			updatePlayerStmt = connection
					.prepareStatement("UPDATE PLAYER SET club_id = ? WHERE id = ?");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	
	public int deletePlayer(Player player) {
		int count = 0;
			try {
				deletePlayerStmt.setLong(1, player.getId());
				count = deletePlayerStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return count;
	}
	
	void clearPlayers() {
		try {
			deleteAllPlayersStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addPlayer(Player player) {
		int count = 0;
		try {
			addPlayerStmt.setString(1, player.getName());
			addPlayerStmt.setString(2, player.getLastName());
			addPlayerStmt.setString(3, player.getPosition());
			addPlayerStmt.setInt(4, player.getAge());
			addPlayerStmt.setLong(5, player.getClubID());

			count = addPlayerStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Player getPlayer(long id) {
		
		try {
			getPlayerStmt.setLong(1, id);
			
			ResultSet rs = getPlayerStmt.executeQuery();
			
			Player p = new Player();
			rs.next();
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setLastName(rs.getString("lastName"));
			p.setPosition(rs.getString("position"));
			p.setAge(rs.getInt("age"));
			
			rs.close();
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
		
	}

	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<Player>();

		try {
			ResultSet rs = getAllPlayersStmt.executeQuery();

			while (rs.next()) {
				Player p = new Player();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setLastName(rs.getString("lastName"));
				p.setPosition(rs.getString("position"));
				p.setAge(rs.getInt("age"));
				players.add(p);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}
	
	public int updatePlayer(Player player, long club_id) {
		int count = 0;
		try {
			updatePlayerStmt.setLong(1, club_id);
			updatePlayerStmt.setLong(2, player.getId());
			count = updatePlayerStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int deletelayerClub(Player player) {
		int count = 0;
		try {
			updatePlayerStmt.setNull(1, java.sql.Types.INTEGER);
			updatePlayerStmt.setLong(2, player.getId());
			count = updatePlayerStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
