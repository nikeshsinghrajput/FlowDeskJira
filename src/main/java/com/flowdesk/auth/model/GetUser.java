package com.flowdesk.auth.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.annotation.Generated;

/**
 * GetUser
 */

@JsonTypeName("getUser")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GetUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	private String password;

	public GetUser(Long id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public GetUser id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */

	// @Schema(name = "id", example = "1", requiredMode =
	// Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GetUser username(String username) {
		this.username = username;
		return this;
	}

	/**
	 * The username of a registered user.
	 * 
	 * @return username
	 */

	// @Schema(name = "username", example = "fehguy", description = "The username of
	// a registered user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public GetUser email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * The email address of a registered user.
	 * 
	 * @return email
	 */
	@jakarta.validation.constraints.Email
	// @Schema(name = "email", example = "user@example.com", description = "The
	// email address of a registered user.", requiredMode =
	// Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GetUser password(String password) {
		this.password = password;
		return this;
	}

	/**
	 * The password of a registered user.
	 * 
	 * @return password
	 */

	// @Schema(name = "password", description = "The password of a registered
	// user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		GetUser getUser = (GetUser) o;
		return Objects.equals(this.id, getUser.id) && Objects.equals(this.username, getUser.username)
				&& Objects.equals(this.email, getUser.email) && Objects.equals(this.password, getUser.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email, password);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetUser {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
