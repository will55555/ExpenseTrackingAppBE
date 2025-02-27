package com.WTT.ExpenseTrackingAppBE.config;

import com.WTT.ExpenseTrackingAppBE.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Log that the authorization header is missing or invalid
            logger.debug("No Authorization header or invalid format.");
            filterChain.doFilter(request, response);
            return;
        }

        // Extract JWT token from the header
        String jwt = authHeader.substring(7);
        String userName = jwtService.extractUserName(jwt);

        if (userName == null || userName.isEmpty()) {
            logger.debug("JWT Token extraction failed or user not found in token.");
            filterChain.doFilter(request, response);
            return;
        }

        // Check if user is already authenticated
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            logger.debug("User details loaded: {}");

            // Validate JWT token
            boolean isValid = jwtService.isTokenValid(jwt, (User) userDetails);
            if (isValid) {
                // Create and set authentication token
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                logger.debug("JWT Token validated and authentication set for user: {}");
            } else {
                logger.debug("JWT Token is invalid.");
            }
        }

        // Continue with the request
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Skip the login endpoint to avoid unnecessary checks
        return request.getRequestURI().equals("/api/users/login");
    }
}