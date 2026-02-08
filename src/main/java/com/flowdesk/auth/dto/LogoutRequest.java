package com.flowdesk.auth.dto;
 

public class LogoutRequest {
	 
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "LogoutRequest [userName=" + userName + "]";
	}

}
