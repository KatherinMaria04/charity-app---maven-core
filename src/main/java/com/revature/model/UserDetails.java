package com.revature.model;

public class UserDetails {
	private String customername;
	private String password;
	
	private long phoneno ;
	
	 
	
	public String getCustomername() {
		return customername;
	}


	public void setCustomername(String customername) {
		this.customername = customername;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}





	public long getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}




	


	public String toString() {
		return "UserDetails[cus_name=" + customername + ", password=" + password + ",phone_num=" + phoneno + "]";
	}
	}
	