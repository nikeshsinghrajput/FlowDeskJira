package com.flowdesk.auth.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;

/**
 * UserRegistration
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String firstName;
	private String lastName;
	private String password;
	private String token;
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String email;
	private String country;
	private String origin;
	 
	 
   
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	 

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserRegistration id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * The userId chosen by the new user.
	 * 
	 * @return id
	 */

	// @Schema(name = "id", example = "298", description = "The userId chosen by the
	// new user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserRegistration username(String username) {
		this.firstName = username;
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
		return firstName;
	}

	public void setUsername(String username) {
		this.firstName = username;
	}

	public UserRegistration password(String password) {
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

	public UserRegistration email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * The email address of the new user.
	 * 
	 * @return email
	 */
	@jakarta.validation.constraints.Email
	// @Schema(name = "email", example = "user@example.io", description = "The email
	// address of the new user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserRegistration userRegistration = (UserRegistration) o;
		return Objects.equals(this.id, userRegistration.id) && Objects.equals(this.firstName, userRegistration.firstName)
				&& Objects.equals(this.password, userRegistration.password)
				&& Objects.equals(this.email, userRegistration.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, password, email);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UserRegistration {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    Origin: ").append(toIndentedString(origin)).append("\n");
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