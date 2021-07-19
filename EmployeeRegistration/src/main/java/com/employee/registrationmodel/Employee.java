package com.employee.registrationmodel;


public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String contact;
	private String address;
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getContact() {
		return contact;
	}
	public String getAddress() {
		return address;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
