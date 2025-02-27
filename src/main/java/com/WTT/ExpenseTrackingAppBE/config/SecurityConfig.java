package com.WTT.ExpenseTrackingAppBE.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless JWT authentication
                .cors(cors -> cors.configurationSource(corsFilter())) // Configure CORS
                .authorizeHttpRequests(ahr ->
                        ahr
                                .requestMatchers(HttpMethod.GET, "/api/stats/**", "/api/budget/all").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/users/login", "/api/expense", "/api/income",
                                        "/api/budget","/api/users/register","/api/expense", "/api/income", "/api/budget").permitAll()


                                .requestMatchers(HttpMethod.PUT, "/api/expense/*", "/api/income/*", "/api/budget/*").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/expense/*", "/api/income/*", "/api/budget/*").permitAll()
                                .anyRequest().authenticated() // Secure all other endpoints
                )
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // Enable credentials if needed
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}