package com.revature.model;

public class AdminLogin {
	private String name;
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
	private String password;
	
	public String toString() {
		return "AdminLogin[name=" + name + ", password=" + password + "]";
	}

}
