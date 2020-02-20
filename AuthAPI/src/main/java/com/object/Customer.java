package com.object;

public class Customer {

	private String name;
	private String pass;
	private String email;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "{\"name\": \"" + name + "\",\"pass\": \"" + pass + "\",\"email\": \"" + email + "\"}";
	}

}
