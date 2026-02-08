package com.flowdesk.auth.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowdesk.auth.dto.GlobalResponse;
import com.flowdesk.auth.dto.LogoutRequest;
import com.flowdesk.auth.entity.UserCredential;
import com.flowdesk.auth.model.GenerateToken;
import com.flowdesk.auth.model.GenerateTokenResponse;
import com.flowdesk.auth.model.Role;
import com.flowdesk.auth.model.UserRegistration;
import com.flowdesk.auth.model.UserRegistrationResponse;
import com.flowdesk.auth.model.ValidateTokenResponse;
import com.flowdesk.auth.repo.UserCredentialRepository;
import com.flowdesk.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
 
 

@RestController
@RequestMapping("/auth")
public class AuthControllers  {

	 
	private static final boolean TOKEN_IS_AUTHINCATED = true;
	private static final boolean TOKEN_IS_UNAUTHINCATED = false;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	LocalDateTime currentDate = LocalDateTime.now();
	
	private static final Logger logger = LoggerFactory.getLogger(AuthControllers.class);
	private AuthenticationManager authenticationManager;

	private UserCredentialRepository userCredentialRepository;

	private AuthService authService;

	 
	 
	@Autowired
	public AuthControllers(AuthenticationManager authenticationManager,
			UserCredentialRepository userCredentialRepository,AuthService authService) {
		this.authenticationManager = authenticationManager;
		this.userCredentialRepository = userCredentialRepository;
		this.authService = authService;
	 
	}
	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponse> registerPost(@RequestBody UserRegistration userRegistration,HttpServletRequest request) {
	    UserRegistrationResponse response = new UserRegistrationResponse();
	    boolean isUserRegistered=false;
	    List<String> to = new LinkedList<String>();
	    try {
	        logger.info("User Response: " + userRegistration.toString());
            Optional<UserCredential> isExistCredential= userCredentialRepository.findByEmail(userRegistration.getEmail()) ;
	        if (isExistCredential.isPresent()) {
	        	logger.info("user already exist");
	        	UserCredential userCredential=isExistCredential.get();
	        	return authService.userExistResponse(userCredential);
	        	  }
	        UserCredential user = new UserCredential(
	            userRegistration.getEmail(),
	            userRegistration.getPassword(),
	            userRegistration.getCountry(),     
	            userRegistration.getFirstName(),
	            userRegistration.getLastName(),
	            userRegistration.getToken()	            
	        );
	            user.setRole(Role.USER);
	             
	            user.setDateAndTime(currentDate.format(formatter));
	        	String token = authService.generateToken(user.getEmail(),TOKEN_IS_UNAUTHINCATED);
		        user.setToken(token);
		         
		        authService.saveUser(user);
	                response.setMessage("Registration successful");
	                response.setSuccess(true);
	                response.setToken(token);
	                return ResponseEntity.ok(response);
	             
	    } catch (Exception exception) {
	        logger.error("Exception occurred during user registration", exception);
	        response.setMessage("Internal server error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@PostMapping("/token")
	public ResponseEntity<GenerateTokenResponse> generateToken(@RequestBody GenerateToken generateToken) {
		logger.info("TokenRequest::{}", generateToken.getUsername());
		GenerateTokenResponse response = new GenerateTokenResponse();
		if (authService.isEnableUser(generateToken.getUsername())) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(generateToken.getUsername(), generateToken.getPassword()));
			logger.info("Authentication ::{}", authentication);
			if (authentication.isAuthenticated()){
				String token = authService.generateToken(generateToken.getUsername(),TOKEN_IS_AUTHINCATED);
                 
				response.setMessage("Sent Successfully");
				response.token(token);
				response.success(true);
				return ResponseEntity.ok(authService.getTokenResponse(response,generateToken));
			} else {
				response.setMessage("Invalid access");
				response.success(false);
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			response.setMessage("Invalid access");
			response.success(false);
			return ResponseEntity.badRequest().body(response);
		}

	}

	@GetMapping("/validate")
	public ResponseEntity<ValidateTokenResponse> validateToken(@RequestParam("token") String token) {
		logger.info("Validate token method start::{}");
		logger.info("user name of this token::{}", authService.getUserNameByToken(token));
		authService.userEnable(token);
		boolean isValid = authService.validateToken(token);
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse();
		try {

			if (isValid) {
				
				validateTokenResponse.message("Token is valid");
				return ResponseEntity.ok(validateTokenResponse);
			} else {
				validateTokenResponse.message("Invalid Token");
				return ResponseEntity.status(400).body(validateTokenResponse);
			}
		} catch (Exception exception) {
			throw new RuntimeException("Validation failed: " + exception.getMessage());
		}
	}

//	@Override
//	@GetMapping("/users")
//	public ResponseEntity<List<GetUser>> getAllUsers() {
//		List<UserCredential> users = userCredentialRepository.findAll();
//
//		List<GetUser> getUsers = users.stream()
//				.map(user -> new GetUser(user.getId(), user.getFirstName(), user.getEmail(), user.getPassword()))
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(getUsers);
//	}
	@PostMapping("/logout")
	public ResponseEntity<GlobalResponse> logoutToken(@Valid @RequestBody LogoutRequest logoutRequest)
	{
		GlobalResponse globalResponse=new GlobalResponse();
		logger.info("logout method start by this user name:{}",logoutRequest);		 
		if(authService.isLogoutUser(logoutRequest.getUserName()))
		{
			globalResponse.setMessage("logout success");
			return ResponseEntity.ok(globalResponse);	  
		}
		else {
			globalResponse.setMessage("user is does not exist");
			return ResponseEntity.status(400).body(globalResponse);
		}
		
		
	}
	
	/*@GetMapping("/forget-password")
	public ResponseEntity<GlobalResponse> forgetPassword(@Valid @RequestParam("userName") @NotBlank(message = "Username cannot be blank") 
	@Email(message = "Invalid email format") String userName) {
		logger.info("forget password method start with this user name:{}",userName);
		GlobalResponse globalResponse=new GlobalResponse();
		try {
		Optional<UserCredential> isExistCredential= userCredentialRepository.findByEmail(userName);
		if(isExistCredential.isPresent())
		{
			String token = authService.generateToken(userName,TOKEN_IS_UNAUTHINCATED);
		 if( messageType.sendPasswordRecetLink(token,userName,isExistCredential.get().getFirstName()))
		 {
			 globalResponse.setMessage("verification mail provided"); 
			 return ResponseEntity.ok(globalResponse);
		 }
		 else {
			 globalResponse.setMessage("mail does not provided");
				return ResponseEntity.status(500).body(globalResponse); 
		 }
		}
		else {
			 globalResponse.setMessage("user not exist");
			return ResponseEntity.status(400).body(globalResponse);
		}}
		catch (Exception e) {
			globalResponse.setMessage("error to sending mail");
			return ResponseEntity.status(400).body(globalResponse);
		}	 
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<GlobalResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
		logger.info("change password method start");
		 
		String userName=authService.getUserNameByToken(changePasswordRequest.getToken());
		logger.info("user name of this token::{}", userName);
		Optional<UserCredential> isExistCredential= userCredentialRepository.findByEmail(userName);
		if(isExistCredential.isPresent())
		{
			Boolean isSuccess= authService.chnagePassword(userName,changePasswordRequest.getNewPassword()) ;
         
         return  isSuccess?ResponseEntity.ok(new GlobalResponse("password chnaged",null)):ResponseEntity.status(400).body(new GlobalResponse(null,"error to changing the password"));
		}
		 return ResponseEntity.status(400).body(new GlobalResponse(null,"error to find user"));
	}*/
	 
}
