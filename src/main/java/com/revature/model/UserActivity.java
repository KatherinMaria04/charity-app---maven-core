package com.revature.model;

public class UserActivity {
	private String customername;
	private String requesttype;
	private long donatingamount;
	
	
public String getCustomername() {
		return customername;
	}


	public void setCustomername(String customername) {
		this.customername = customername;
	}


	public String getRequesttype() {
		return requesttype;
	}


	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}


	public long getDonatingamount() {
		return donatingamount;
	}


	public void setDonatingamount(long donatingamount) {
		this.donatingamount = donatingamount;
	}


public String toString() {
	return "UserActivity[cus_name=" + customername + ", request_type=" + requesttype + ",donating_amt=" + donatingamount + "]";
}
}
