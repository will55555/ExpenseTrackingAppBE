package com.WTT.ExpenseTrackingAppBE;

import com.WTT.ExpenseTrackingAppBE.config.JwtConfigProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfigProperty.class) // Enable configuration properties
public class ExpenseTrackingAppBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackingAppBeApplication.class, args);
	}

}