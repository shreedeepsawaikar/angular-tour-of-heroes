package com.lolxz.model;

public class Hero {
	private int id;
	private String name;
	public Hero () {
		
	}
	
	public int getId() {
		return id;
	}
	public Hero(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
