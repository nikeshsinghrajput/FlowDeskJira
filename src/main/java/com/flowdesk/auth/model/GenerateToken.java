package com.flowdesk.auth.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * GenerateToken
 */

@JsonTypeName("generateToken")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GenerateToken implements Serializable {

	private static final long serialVersionUID = 1L;
	    @Email(message = "Invalid email format")
	    @NotBlank(message = "Username is required")
	    private String username;

	    @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters")
	    private String password;

	public GenerateToken username(String username) {
		this.username = username;
		return this;
	}

	/**
	 * The username chosen by the new user.
	 * 
	 * @return username
	 */

	// @Schema(name = "username", example = "fehguy", description = "The username
	// chosen by the new user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public GenerateToken password(String password) {
		this.password = password;
		return this;
	}

	/**
	 * The password chosen by the new user.
	 * 
	 * @return password
	 */

	// @Schema(name = "password", example = "fehguy", description = "The password
	// chosen by the new user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GenerateToken generateToken = (GenerateToken) o;
		return Objects.equals(this.username, generateToken.username)
				&& Objects.equals(this.password, generateToken.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GenerateToken {\n");
		sb.append("    username: ").append(toIndentedString(username)).append("\n");
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