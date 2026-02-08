package com.flowdesk.auth.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flowdesk.auth.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_credential")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential implements UserDetails {
	@Id
	@SequenceGenerator(name = "login_sequence", sequenceName = "login_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_sequence")
	private Long id;
	private String email;
	@Column(name = "verify", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean verify = false;
	private String token;
	private String password;
	private String country;
	private String origin;
	private String firstName;
	private String lastName;
	private String dateAndTime;
	 
	private boolean isLogout = true;
	 
	@Enumerated(value = EnumType.STRING)
	private Role role;
	
	 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserCredential(@Email String email, String password, String country, String firstName, String lastName,
			String token) {
		this.token=token;
		this.firstName=firstName;
		this.email=email;
		this.password=password;
		this.country=country;
		this.lastName=lastName;
		 
	}

}
