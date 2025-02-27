package com.WTT.ExpenseTrackingAppBE.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Validated // Enables validation for this class
public class JwtConfigProperty {

    // Getters and setters
    @NotBlank(message = "JWT secret key must not be blank")
    private String secret;

    @Positive(message = "JWT expiration time must be a positive value")
    private long expiration;

}