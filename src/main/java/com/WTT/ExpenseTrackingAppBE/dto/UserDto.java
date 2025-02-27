package com.WTT.ExpenseTrackingAppBE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor // Generates a no-args constructor
public class UserDto {
    private long id;
    private String userName;
    private String password;
    private String email;


    // Custom constructor for specific fields
    public UserDto(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;

    }


}