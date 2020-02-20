package com.object;

public class TokenRequestData {

	String name, password, scope;

	public TokenRequestData() {
		// TODO Auto-generated constructor stub
	}
	
	

	public TokenRequestData(String name, String password, String scope) {
		super();
		this.name = name;
		this.password = password;
		this.scope = scope;
	}



	@Override
	public String toString() {
		return "TokenRequestData [name=" + name + ", password=" + password + ", scope=" + scope + "]";
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	

	

	
}