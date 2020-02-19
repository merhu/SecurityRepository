package com.authapi;

public class TokenRequestData {
	@Override
	public String toString() {
		return "TokenRequestData [name=" + name + ", password=" + password + ", scopes=" + scopes + ", email=" + email
				+ "]";
	}

	String name;
	String password;
	String scopes;
	String email;
	
	public TokenRequestData(String username, String password, String email) {
		super();
		this.name = username;
		this.password = password;
		this.email = email;
	}
	
	public TokenRequestData() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
