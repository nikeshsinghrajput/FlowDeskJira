package com.flowdesk.auth.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;

/**
 * UserRegistrationResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class UserRegistrationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean success;

	private String message;

	private String token;
	

	public UserRegistrationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegistrationResponse(Boolean success, String message, String token) {
		super();
		this.success = success;
		this.message = message;
		this.token = token;
	}

	public UserRegistrationResponse success(Boolean success) {
		this.success = success;
		return this;
	}

	/**
	 * Indicates whether the user registration was successful.
	 * 
	 * @return success
	 */

	// @Schema(name = "success", example = "true", description = "Indicates whether
	// the user registration was successful.", requiredMode =
	// Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public UserRegistrationResponse message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * A message describing the result of the registration attempt.
	 * 
	 * @return message
	 */

	// @Schema(name = "message", example = "User registered successfully.",
	// description = "A message describing the result of the registration attempt.",
	// requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserRegistrationResponse token(String token) {
		this.token = token;
		return this;
	}

	/**
	 * An authentication token generated for the registered user. Only present on
	 * successful registration.
	 * 
	 * @return token
	 */

	// @Schema(name = "token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
	// description = "An authentication token generated for the registered user.
	// Only present on successful registration.", requiredMode =
	// Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserRegistrationResponse userRegistrationResponse = (UserRegistrationResponse) o;
		return Objects.equals(this.success, userRegistrationResponse.success)
				&& Objects.equals(this.message, userRegistrationResponse.message)
				&& Objects.equals(this.token, userRegistrationResponse.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(success, message, token);
	}

	@Override
	public String toString() {
		return "UserRegistrationResponse [success=" + success + ", message=" + message + ", token=" + token + "]";
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
	
}
