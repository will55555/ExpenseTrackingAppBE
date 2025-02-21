package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.AuthorizationRequest;
import com.WTT.ExpenseTrackingAppBE.dto.PostNewUser;
import com.WTT.ExpenseTrackingAppBE.dto.UserDto;
import com.WTT.ExpenseTrackingAppBE.services.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Data
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> postNewUser(@RequestBody PostNewUser postNewUser) {
        UserDto createdUser = userService.createUser(postNewUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthorizationRequest request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }
}