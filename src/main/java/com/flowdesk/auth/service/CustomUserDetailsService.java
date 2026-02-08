package com.flowdesk.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flowdesk.auth.entity.UserCredential;
import com.flowdesk.auth.repo.UserCredentialRepository;

 

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Find the user credential by username
		UserCredential user = repository.findByUsername(username);

		if (user != null) {

			return user;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public void verifyUser(String username, String token) {
		// Find the user by username
		UserCredential user = repository.findByUsername(username);

		if (user != null) {
			// Update the token
			user.setToken(token);
			// Save the updated user back to the database
			repository.save(user);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}