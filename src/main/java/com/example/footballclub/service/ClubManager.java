package com.example.footballclub.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.footballclub.domain.Club;
import com.example.footballclub.domain.Player;

public class ClubManager {

	private Connection connection;
	
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTablePlayer = "CREATE TABLE CLUB(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name varchar(40), founded integer, league varchar(30))";

	private PreparedStatement addClubStmt;
	private PreparedStatement deleteClubStmt;
	private PreparedStatement deleteAllClubsStmt;
	private PreparedStatement getClubStmt;
	private PreparedStatement getAllClubsStmt;
	private PreparedStatement updateClubStmt;
	private PreparedStatement getAllClubPlayersStmt;
	
	private Statement statement;

	public ClubManager() {
		super();
		try {
		connection = DriverManager.getConnection(url);
		
			statement = connection.createStatement();
	

		ResultSet rs = connection.getMetaData().getTables(null, null, null,
				null);
		boolean tableExists = false;
		while (rs.next()) {
			if ("CLUB".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
				tableExists = true;
				break;
			}
		}

		if (!tableExists)
			statement.executeUpdate(createTablePlayer);

		addClubStmt = connection
				.prepareStatement("INSERT INTO CLUB (name, founded, league) VALUES (?, ?, ?)");
		deleteAllClubsStmt = connection
				.prepareStatement("DELETE FROM CLUB");
		deleteClubStmt = connection
				.prepareStatement("DELETE FROM CLUB WHERE id = ?");
		getClubStmt = connection
				.prepareStatement("SELECT name, founded, league FROM CLUB WHERE id = ?");
		getAllClubsStmt = connection
				.prepareStatement("SELECT id, name, founded, league FROM CLUB");
		updateClubStmt = connection
				.prepareStatement("UPDATE CLUB SET league = ? WHERE id = ?");
	//	getAllClubsStmt = connection.prepareStatement("SELECT id, name, lastName, position, age FROM CLUB WHERE club_id = ?");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	Connection getConnection() {
		return connection;
	}

	
	public int deleteClub(Club club) {
		int count = 0;
			try {
				deleteClubStmt.setLong(1, club.getId());
				count = deleteClubStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return count;
	}
	
	void clearClubs() {
		try {
			deleteAllClubsStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addClub(Club club) {
		int count = 0;
		try {
			addClubStmt.setString(1, club.getName());
			addClubStmt.setInt(2, club.getFounded());
			addClubStmt.setString(3, club.getLeague());

			count = addClubStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Club getClub(long id) {
		
		try {
			getClubStmt.setLong(1, id);
			
			ResultSet rs = getClubStmt.executeQuery();
			
			Club c = new Club();
			rs.next();
			c.setId(rs.getLong("id"));
			c.setName(rs.getString("name"));
			c.setFounded(rs.getInt("founded"));
			c.setLeague(rs.getString("league"));			
			rs.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
		
	}

	public List<Club> getAllClubs() {
		List<Club> clubs = new ArrayList<Club>();

		try {
			ResultSet rs = getAllClubsStmt.executeQuery();

			while (rs.next()) {
				Club c = new Club();
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				c.setFounded(rs.getInt("founded"));
				c.setLeague(rs.getString("league"));	
				clubs.add(c);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clubs;
	}
	
	public int updateClub(Club club, String league) {
		int count = 0;
		try {
			updateClubStmt.setString(1, league);
			updateClubStmt.setLong(2, club.getId());
			count = updateClubStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<Player> getClubPlayers() {
		List<Player> players = new ArrayList<Player>();

		try {
			getAllClubPlayersStmt.setInt(1,  0);
			ResultSet rs = getAllClubPlayersStmt.executeQuery();

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

}
	
