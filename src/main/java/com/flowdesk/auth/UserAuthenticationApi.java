//package com.flowdesk.auth;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.hibernate.annotations.Parameter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.request.NativeWebRequest;
//
//import com.flowdesk.auth.model.GenerateToken;
//import com.flowdesk.auth.model.GenerateTokenResponse;
//import com.flowdesk.auth.model.GetUser;
//import com.flowdesk.auth.model.UserRegistration;
//import com.flowdesk.auth.model.UserRegistrationResponse;
//import com.flowdesk.auth.model.ValidateTokenResponse;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.annotation.Generated;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//
//@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
//@Validated
//@Tag(name = "User Authentication", description = "Resources related to user authentication")
//public interface UserAuthenticationApi {
//
//    default Optional<NativeWebRequest> getRequest() {
//        return Optional.empty();
//    }
//
//    /**
//     * POST /auth/token : Generate Token
//     * This endpoint generates a token for registered users in the system. It does not require any request body or parameters. 
//     *
//     * @param generateToken User registration details (required)
//     * @return Token Generated Successfully (status code 200)
//     *         or Invalid Authentication (status code 400)
//     *         or Internal Server Error (status code 500)
//     */
//    @Operation(
//        operationId = "generateToken",
//        summary = "Generate Token",
//        description = "This endpoint generates a token for registered users in the system. It does not require any request body or parameters. ",
//        tags = { "User Authentication" },
//        responses = {
//            @ApiResponse(responseCode = "200", description = "Token Generated Successfully", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = GenerateTokenResponse.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Invalid Authentication", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = GenerateTokenResponse.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = GenerateTokenResponse.class))
//            })
//        }
//    )
//    @RequestMapping(
//        method = RequestMethod.POST,
//        value = "/auth/token",
//        produces = { "application/json" },
//        consumes = { "application/json" }
//    )
//    default ResponseEntity<GenerateTokenResponse> _generateToken(
//        @Parameter(name = "GenerateToken", description = "User registration details", required = true) @Valid @RequestBody GenerateToken generateToken
//    ) {
//        return generateToken(generateToken);
//    }
//
//    @PostMapping("/register")
//    ResponseEntity<UserRegistrationResponse> registerPost(@RequestBody UserRegistration userRegistration,HttpServletRequest request, @RequestHeader(value = "Origin", required = false) String origin);
//
//    // Override this method
//    default  ResponseEntity<GenerateTokenResponse> generateToken(GenerateToken generateToken) {
//        getRequest().ifPresent(request -> {
//            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
//                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
//                    String exampleString = "{ \"success\" : true, \"message\" : \"Token generated successfully.\", \"token\" : \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\" }";
//                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
//                    break;
//                }
//            }
//        });
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//
//    }
//
//
//    /**
//     * GET /auth/getUser : Get a List of All Users
//     * This endpoint retrieves a list of all registered users in the system. It does not require any request body or parameters. 
//     *
//     * @return List of User Data (status code 200)
//     */
//    @Operation(
//        operationId = "getAllUsers",
//        summary = "Get a List of All Users",
//        description = "This endpoint retrieves a list of all registered users in the system. It does not require any request body or parameters. ",
//        tags = { "User Authentication" },
//        responses = {
//            @ApiResponse(responseCode = "200", description = "List of User Data", content = {
//                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetUser.class)))
//            })
//        }
//    )
//    @RequestMapping(
//        method = RequestMethod.GET,
//        value = "/auth/getUser",
//        produces = { "application/json" }
//    )
//    default ResponseEntity<List<GetUser>> _getAllUsers(
//        
//    ) {
//        return getAllUsers();
//    }
//
//    // Override this method
//    default  ResponseEntity<List<GetUser>> getAllUsers() {
//        getRequest().ifPresent(request -> {
//            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
//                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
//                    String exampleString = "[ { \"password\" : \"password\", \"id\" : 1, \"email\" : \"user@example.com\", \"username\" : \"fehguy\" }, { \"password\" : \"password\", \"id\" : 1, \"email\" : \"user@example.com\", \"username\" : \"fehguy\" } ]";
//                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
//                    break;
//                }
//            }
//        });
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//
//    }
//
//
//    /**
//     * POST /auth/register : Register a New User
//     * This endpoint allows users to register for the service. After successful registration, a confirmation email is sent. This API handles user registration and related operations. 
//     *
//     * @param userRegistration User registration details (required)
//     * @return User Registration Successful (status code 200)
//     *         or Invalid Registration Data (status code 400)
//     *         or Internal Server Error (status code 500)
//     */
//    @Operation(
//        operationId = "registerUser",
//        summary = "Register a New User",
//        description = "This endpoint allows users to register for the service. After successful registration, a confirmation email is sent. This API handles user registration and related operations. ",
//        tags = { "User Authentication" },
//        responses = {
//            @ApiResponse(responseCode = "200", description = "User Registration Successful", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegistrationResponse.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Invalid Registration Data", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegistrationResponse.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegistrationResponse.class))
//            })
//        }
//    )
//    @RequestMapping(
//        method = RequestMethod.POST,
//        value = "/auth/register",
//        produces = { "application/json" },
//        consumes = { "application/json" }
//    )
//    default ResponseEntity<UserRegistrationResponse> _registerUser(
//        @Parameter(name = "UserRegistration", description = "User registration details", required = true) @Valid @RequestBody UserRegistration userRegistration
//    ) {
//        return registerUser(userRegistration);
//    }
//
//    // Override this method
//    default  ResponseEntity<UserRegistrationResponse> registerUser(UserRegistration userRegistration) {
//        getRequest().ifPresent(request -> {
//            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
//                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
//                    String exampleString = "{ \"success\" : true, \"message\" : \"User registered successfully.\", \"token\" : \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\" }";
//                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
//                    break;
//                }
//            }
//        });
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//
//    }
//
//
//    /**
//     * GET /auth/validate : Validate Token
//     * This endpoint validates a token for registered users in the system. It does not require any request body or parameters. 
//     *
//     * @param token The token to be validated (required)
//     * @return Token is Valid (status code 200)
//     *         or Invalid Token (status code 400)
//     *         or Internal Server Error (status code 500)
//     */
//    @Operation(
//        operationId = "validateToken",
//        summary = "Validate Token",
//        description = "This endpoint validates a token for registered users in the system. It does not require any request body or parameters. ",
//        tags = { "User Authentication" },
//        responses = {
//            @ApiResponse(responseCode = "200", description = "Token is Valid", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = ValidateTokenResponse.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Invalid Token", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = ValidateTokenResponse.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
//                @Content(mediaType = "application/json", schema = @Schema(implementation = ValidateTokenResponse.class))
//            })
//        }
//    )
//    @RequestMapping(
//        method = RequestMethod.GET,
//        value = "/auth/validate",
//        produces = { "application/json" }
//    )
//    default ResponseEntity<ValidateTokenResponse> _validateToken(
//        @NotNull @Parameter(name = "token", description = "The token to be validated", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "token", required = true) String token
//    ) {
//        return validateToken(token);
//    }
//
//    // Override this method
//    default  ResponseEntity<ValidateTokenResponse> validateToken(String token) {
//        getRequest().ifPresent(request -> {
//            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
//                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
//                    String exampleString = "{ \"message\" : \"message\" }";
//                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
//                    break;
//                }
//            }
//        });
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//
//    }
//
//}