package com.flowdesk.auth.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.annotation.Generated;

/**
 * GenerateTokenResponse
 */

@JsonTypeName("generateTokenResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GenerateTokenResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean success;

	private String message;

	private String token;
	private String country;
	private String firstName;
	private String lastName;
	private String dateAndTime;
	private String role;
	private long totalDocument;
	private String signWith;
	
	
	public String getSignWith() {
		return signWith;
	}

	public void setSignWith(String signWith) {
		this.signWith = signWith;
	}

	public long getTotalDocument() {
		return totalDocument;
	}

	public void setTotalDocument(long totalDocument) {
		this.totalDocument = totalDocument;
	}

	 
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GenerateTokenResponse success(Boolean success) {
		this.success = success;
		return this;
	}

	/**
	 * Indicates whether the token was generated successfully.
	 * 
	 * @return success
	 */

	// @Schema(name = "success", example = "true", description = "Indicates whether
	// the token was generated successfully.", requiredMode =
	// Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public GenerateTokenResponse message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * A message describing the result of the token generation attempt.
	 * 
	 * @return message
	 */

	// @Schema(name = "message", example = "Token generated successfully.",
	// description = "A message describing the result of the token generation
	// attempt.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GenerateTokenResponse token(String token) {
		this.token = token;
		return this;
	}

	/**
	 * An authentication token generated for the user. Only present on successful
	 * token generation.
	 * 
	 * @return token
	 */

	// @Schema(name = "token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
	// description = "An authentication token generated for the user. Only present
	// on successful token generation.", requiredMode =
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
		GenerateTokenResponse generateTokenResponse = (GenerateTokenResponse) o;
		return Objects.equals(this.success, generateTokenResponse.success)
				&& Objects.equals(this.message, generateTokenResponse.message)
				&& Objects.equals(this.token, generateTokenResponse.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(success, message, token);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GenerateTokenResponse {\n");
		sb.append("    success: ").append(toIndentedString(success)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    token: ").append(toIndentedString(token)).append("\n");
		sb.append("}");
		return sb.toString();
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