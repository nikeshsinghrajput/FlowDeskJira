package com.flowdesk.auth.entity;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.flowdesk.auth.config.AuthConfig;
import com.flowdesk.auth.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		
		 String requestURI = request.getRequestURI();

         // Check if the request matches a public URL pattern
         if (isPublicUrl(requestURI)) {
             // Skip the filter for public URLs
        	 logger.info("Skip the filter for public URLs ::{}", request.getRequestURI());
             filterChain.doFilter(request, response);
             return;
         }
		logger.info("filter start");
 		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			logger.error("when header is not comming with request::{} ",request.getRequestURI());
			filterChain.doFilter(request, response);
			return;
		}
		String username=null;
		String token = authHeader.substring(7);
		try {
		 username = jwtService.extractUsername(token);
		}catch(Exception e)
		{
			logger.error("when Token is invalid or expired with this uri::{} ",request.getRequestURI());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Token is invalid or expired");
			return;	
		}
		logger.info("getting user name from token ::{}",username);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			logger.info("::{}", userDetails.toString());
			UserCredential userCredential = (UserCredential) userDetails;
			if (!userCredential.getVerify()) {
				logger.error("when {} account is disabled with this uri::{} ",userCredential.getEmail(), request.getRequestURI());
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.getWriter().write("User account is disabled.");
				return;
			}
			if (userCredential.isLogout()) {
				logger.error("when {} account is disabled with this uri::{} ",userCredential.getEmail(),request.getRequestURI());
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.getWriter().write("Please login again");
				return;
			}
			logger.info("user credntial checking complete");
			if (jwtService.isValid(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				logger.info("authToken::{}",authToken);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	private boolean isPublicUrl(String requestURI) {
	    AntPathMatcher antPathMatcher = new AntPathMatcher();

	    // Convert the String[] to a Stream and check if the requestURI matches any public URL pattern
	    return Arrays.stream(AuthConfig.PUBLIC_URL).anyMatch(publicUrl -> antPathMatcher.match(publicUrl, requestURI));
	}

}
