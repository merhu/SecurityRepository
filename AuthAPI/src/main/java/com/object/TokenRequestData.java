package com.object;

public class TokenRequestData {

	String name, pass, scope;

	public TokenRequestData(String name, String pass, String scope) {
		super();
		this.name = name;
		this.pass = pass;
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}