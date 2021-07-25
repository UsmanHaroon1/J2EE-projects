package com.users.model;

public class User {
	
	private int id;
	private String name;
	private String email;
	private String city;
	
	
	public User() {
		super();		
	}

	public User(String name, String email, String city) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
	}


	public User(int id, String name, String email, String city) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.city = city;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getCity() {
		return city;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", city=" + city + "]";
	}
}
