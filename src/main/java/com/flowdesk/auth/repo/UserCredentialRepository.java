package com.flowdesk.auth.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flowdesk.auth.entity.UserCredential;

import jakarta.transaction.Transactional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
	@Query("SELECT uc FROM UserCredential uc WHERE uc.email = :email")
	UserCredential findByUsername(@Param("email") String email);

	<S extends UserCredential> Page<S> findAll(Example<S> example, Pageable pageable);

	@Query("SELECT uc FROM UserCredential uc WHERE uc.email = :email AND uc.token = :token")
	Optional<UserCredential> findByUsernameAndToken(@Param("email") String email, @Param("token") String token);

	Optional<UserCredential> findByEmail(@Param("email") String email);

	@Transactional
	default void verifyUserByToken(@Param("token") String token) {
		// Retrieve the UserCredential by token
		UserCredential userCredential = findByToken(token);

		// Check if the userCredential exists

		if (userCredential != null) {
			// Set the verify attribute to true
			userCredential.setVerify(true);

			// Save the updated userCredential back to the database
			save(userCredential);
		}
	}

	@Query("SELECT uc FROM UserCredential uc WHERE uc.token = :token")
	UserCredential findByToken(@Param("token") String token);

//	@Query("SELECT new com.plusyoursoftech.ds.dto.UserCredentialDTO(u.id, u.email, u.verify, u.token, u.country, u.origin, u.firstName, u.lastName, u.documentAlowence, u.isLogout, u.role) FROM UserCredential u")
//    List<UserCredentialDTO> findAllUserCredentialDTOs();
	
	@Query(value = "SELECT ad.user_credential_id FROM auth_document ad " +
            "WHERE ad.document_details_id = :documentId", nativeQuery = true)
	Long findUserCredentialIdsByDocumentId(@Param("documentId") Long documentId);
}