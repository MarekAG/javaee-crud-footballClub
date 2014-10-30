package com.example.footballclub.domain;

import java.util.List;

public class Club {
	
	private long id;

	private String name;
	private int founded;
	private String league;
	private List<Player> players;
	
	public Club() {
		super();
	}
	
	public Club(String name, int founded, String league) {
		super();
		this.name = name;
		this.founded = founded;
		this.league = league;
	}

	public Club(String name, int founded, String league, List<Player> players) {
		super();
		this.name = name;
		this.founded = founded;
		this.league = league;
		this.players = players;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFounded() {
		return founded;
	}
	public void setFounded(int founded) {
		this.founded = founded;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
}
