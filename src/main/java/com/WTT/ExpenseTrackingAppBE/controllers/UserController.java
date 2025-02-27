package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.AuthorizationRequest;
import com.WTT.ExpenseTrackingAppBE.dto.PostNewUser;
import com.WTT.ExpenseTrackingAppBE.dto.UserDto;
import com.WTT.ExpenseTrackingAppBE.services.user.UserService;
import com.WTT.ExpenseTrackingAppBE.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserServiceImpl userService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<UserDto> postNewUser(@RequestBody PostNewUser postNewUser) {
        UserDto registeredUser = userService.registerUser(postNewUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthorizationRequest request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }


}