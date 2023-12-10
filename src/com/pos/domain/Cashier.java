package com.pos.domain;

public class Cashier {
	private String id;
	private String name;
	
	public Cashier(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
