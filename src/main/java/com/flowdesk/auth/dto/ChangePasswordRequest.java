package com.flowdesk.auth.dto;

 

public class ChangePasswordRequest {
	 
	private String token;
	 
	private String newPassword;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ChangePasswordRequest [token=" + token + ", newPassword=" + newPassword.length() + " characters]";
	}
	
	
}
