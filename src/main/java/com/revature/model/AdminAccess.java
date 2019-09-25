package com.revature.model;

public class AdminAccess {
	private String requesttype;
	private long amountrequested;
	private long targetamount;
	public long getTargetamount() {
		return targetamount;
	}



	public void setTargetamount(long targetamount) {
		this.targetamount = targetamount;
	}
	
	
	
	public String getRequesttype() {
		return requesttype;
	}



	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}



	public long getAmountrequested() {
		return amountrequested;
	}



	public void setAmountrequested(long amountrequested) {
		this.amountrequested = amountrequested;
	}



	



	public String toString() {
		
		return "adminDetails[request_type=" + requesttype + ", amt_requested=" + amountrequested + ", target_amt=" + targetamount + "]";
	}
	
}
