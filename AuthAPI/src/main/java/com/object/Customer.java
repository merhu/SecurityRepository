package com.object;

public class Customer {

	int id;
	private String name;
	private String password;
	private String email;

	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "{\"name\": \"" + name + "\",\"password\": \"" + password + "\",\"email\": \"" + email + "\"}";
	}

}
