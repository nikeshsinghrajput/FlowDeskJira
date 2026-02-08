package com.flowdesk.auth.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.annotation.Generated;

/**
 * ValidateTokenResponse
 */

@JsonTypeName("validateTokenResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ValidateTokenResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	public ValidateTokenResponse message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Get message
	 * 
	 * @return message
	 */

	// @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ValidateTokenResponse validateTokenResponse = (ValidateTokenResponse) o;
		return Objects.equals(this.message, validateTokenResponse.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ValidateTokenResponse {\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
