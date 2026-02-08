package com.flowdesk.auth.dto;

public class GlobalResponse {

	private String message;
    private String error;
    
    
	public GlobalResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GlobalResponse(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "GlobalResponse [message=" + message + ", error=" + error + "]";
	}
	
	 
	
}
