package com.flowdesk.auth.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flowdesk.auth.entity.JwtService;
import com.flowdesk.auth.entity.UserCredential;
import com.flowdesk.auth.model.GenerateToken;
import com.flowdesk.auth.model.GenerateTokenResponse;
import com.flowdesk.auth.model.UserRegistrationResponse;
import com.flowdesk.auth.repo.UserCredentialRepository;

 
@Service
public class AuthService {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	LocalDateTime currentDate = LocalDateTime.now();
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
	private static final String HTTPS_WWW_GOOGLE_COM ="https://www.google.com";
	private static final String HTTPS_ALFRESCO ="https://www.alfresco.com";
	private static final boolean TOKEN_IS_UNAUTHINCATED = false;
	private final UserCredentialRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	 
	@Value("${ds.basic.total.document}")
	private  long basicPlanDocument;
	@Value("${ds.enterprise.total.document}")
	private  long enterprisePlanDocument ;
	@Value("${ds.default.total.document}")
	private  long  defaultAllowedDocument ;
	@Autowired
	public AuthService(UserCredentialRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		 
	}

	public String saveUser(UserCredential credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "user added to the system";
	}

	public String generateToken(String username,boolean isAuthincating) {
		return jwtService.generateToken(username,isAuthincating);
	}

	public boolean validateToken(String token) {
		logger.info(token);
		jwtService.validateToken(token);
		return true;
	}

	public void userEnable(String token) {
		logger.info("searching token in database::{}", token);
		repository.verifyUserByToken(token);
//		UserCredential userCredential=repository.findByToken(token) ;
//		logger.info("searching credential in database::{}",userCredential);
//		userCredential.setVerify(true);
//		repository.saveAndFlush(userCredential);

	}

	public String getUserNameByToken(String token) {
		return jwtService.extractUsername(token);
	}

	public boolean isEnableUser(String username) {
		try {
			UserCredential userCredential = repository.findByEmail(username).orElseThrow();
			userCredential.setLogout(false);
			logger.info("*::{}", userCredential.toString());
			repository.save(userCredential);
			return userCredential.getVerify();
		} catch (Exception e) {
			logger.error("isEnableUser:",e);
			return false;
		}
	}

	public boolean isLogoutUser(String userName) {
		try {
			UserCredential userCredential = repository.findByEmail(userName).orElseThrow(()->new NotFoundException());
			logger.info("user credential {} which retriving by this username {}",userCredential,userName);
			userCredential.setLogout(true);
			repository.save(userCredential);
			return true;
		} catch (Exception e) {
			logger.error("isLogoutUser :",e);
			return false;
		}
	}

	public Boolean chnagePassword(String userName, String newPassword) {
		try {
			UserCredential userCredential = repository.findByEmail(userName).orElseThrow();
			logger.info("user credential {} which retriving by this username {}",userCredential,userName);
			userCredential.setPassword(passwordEncoder.encode(newPassword));
			repository.save(userCredential);
			return true;
		} catch (Exception e) {
			logger.error("chnagePassword:",e);
			return false;
		}

	}

//	public void setDocumentAlowence(UserCredential user) {
//		try {
//			List<PaymentDetail> paymentDetails = paymentDetailRepo.findByEmail(user.getEmail());
//			if (!paymentDetails.isEmpty()) {
//				PaymentDetail paymentDetail = paymentDetails.get(0);
//				logger.info("payment detail while set allowent to register:{}", paymentDetail);
//				if (PaymentStatus.SUCCSS.name().equalsIgnoreCase(paymentDetail.getStatus())
//						&& ConstantVariable.BASIC.equalsIgnoreCase(paymentDetail.getPlanType())) {
//					user.setDocumentAlowence(basicPlanDocument);
//				} else if (PaymentStatus.SUCCSS.name().equalsIgnoreCase(paymentDetail.getStatus())
//						&& ConstantVariable.ENTERPRISE.equalsIgnoreCase(paymentDetail.getPlanType())) {
//					user.setDocumentAlowence(enterprisePlanDocument);
//				}
//
//				else {
//					user.setDocumentAlowence(defaultAllowedDocument);
//				}
//			} else {
//				user.setDocumentAlowence(defaultAllowedDocument);
//			}
//			saveUser(user);
//			logger.info("register user info while save into data base:{}", user);
//
//		} catch (Exception e) {
//			logger.error("error while set alowence in data base", e);
//		}
//	}
	
//	public List<UserCredentialDTO> getAllUser(){
//		logger.info("Getting all user successful");
//		return repository.findAllUserCredentialDTOs();
//	}
//	
	public UserCredential updateUser(int id, UserCredential userCredential) {
		return repository.findById(id).map(user -> {
			user.setEmail(userCredential.getEmail());
			user.setCountry(userCredential.getCountry());
			user.setOrigin(userCredential.getOrigin());
			user.setFirstName(userCredential.getFirstName());
			user.setLastName(userCredential.getLastName());
			
			user.setRole(userCredential.getRole());
			return repository.save(user);
		}).orElseThrow(() -> new RuntimeException( "user not found with id " + id));
	}

	public GenerateTokenResponse getTokenResponse(GenerateTokenResponse response, GenerateToken generateToken) {
		UserCredential userCredential = repository.findByEmail(generateToken.getUsername()).orElseThrow();
		response.setCountry(userCredential.getCountry());
		response.setFirstName(userCredential.getFirstName());
		response.setLastName(userCredential.getLastName());
		response.setDateAndTime(currentDate.format(formatter));
		response.setRole(userCredential.getRole().name());
		 
		return response;
	}

	public ResponseEntity<UserRegistrationResponse> userExistResponse(UserCredential userCredential) {
		logger.info(HTTPS_ALFRESCO+"    "+userCredential);
		if(HTTPS_WWW_GOOGLE_COM.equalsIgnoreCase(userCredential.getOrigin()))
    	{
    	      isEnableUser(userCredential.getEmail());
            return ResponseEntity.ok(new UserRegistrationResponse(true, "User already exists", generateToken(userCredential.getEmail(),TOKEN_IS_UNAUTHINCATED)));
    	}
    	else if(HTTPS_ALFRESCO.equalsIgnoreCase(userCredential.getOrigin()))
    	{
    		 isEnableUser(userCredential.getEmail());
            return ResponseEntity.ok(new UserRegistrationResponse(true, "User already exists",  generateToken(userCredential.getEmail(),TOKEN_IS_UNAUTHINCATED)));
    	}
        	else
        	{
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserRegistrationResponse(false, "User already exists", null ));  
        	}
		 
	}

}