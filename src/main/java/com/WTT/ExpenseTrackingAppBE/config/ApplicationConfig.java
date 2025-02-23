package com.WTT.ExpenseTrackingAppBE.config;

import com.WTT.ExpenseTrackingAppBE.exceptions.UserNotFoundException;
import com.WTT.ExpenseTrackingAppBE.repos.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for application-wide beans and security settings.
 * This class sets up the necessary components for authentication and user management.
 *
 * Key points:
 * 1. Uses @Configuration to indicate this is a configuration class.
 * 2. Configures UserDetailsService, AuthenticationProvider, AuthenticationManager, and PasswordEncoder.
 * 3. Integrates with UserCredentialRepository for user lookup.
 * 4. Implements custom user lookup logic with exception handling.
 */
@Configuration
public class ApplicationConfig {

    private final UserRepo userRepo;

    public ApplicationConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Configures and provides a UserDetailsService bean.
     * This service is responsible for retrieving user details by username.
     *
     * @return A UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userName -> userRepo.findByUserName(userName).orElseThrow(() ->
                new UserNotFoundException("User with username " + userName + " not found"));
    }

    /**
     * Configures and provides an AuthenticationProvider bean.
     * This provider is responsible for authenticating users.
     *
     * @return A configured DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Configures and provides an AuthenticationManager bean.
     * This manager is responsible for processing Authentication requests.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration to use.
     * @return An AuthenticationManager instance.
     * @throws Exception If there's an error creating the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures and provides a PasswordEncoder bean.
     * This encoder is used for securely hashing passwords.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}