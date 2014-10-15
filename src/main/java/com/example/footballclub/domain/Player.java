package com.example.footballclub.domain;

public class Player {
	
	private long id;
	
	private String name;
	private String lastName;
	private String position;
	private int age;
	
	public Player() {
	}
	
	public Player(String name, String lastName, String position, int age) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.position = position;
		this.age = age;
		
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
