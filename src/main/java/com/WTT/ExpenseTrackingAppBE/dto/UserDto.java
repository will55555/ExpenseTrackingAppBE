package com.WTT.ExpenseTrackingAppBE.dto;

import lombok.Data;

//Data Transfer Object (DTO) for User information.
//Immutable design with final fields and no setters.
//Contains only essential user information
@Data
public class UserDto {
    private final String userName;
    private final String role;

}