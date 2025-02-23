package com.WTT.ExpenseTrackingAppBE.services;

import com.WTT.ExpenseTrackingAppBE.config.JwtService;
import com.WTT.ExpenseTrackingAppBE.dto.AuthorizationRequest;
import com.WTT.ExpenseTrackingAppBE.dto.PostNewUser;
import com.WTT.ExpenseTrackingAppBE.dto.UserDto;
import com.WTT.ExpenseTrackingAppBE.entities.User;
import com.WTT.ExpenseTrackingAppBE.exceptions.InvalidRoleException;
import com.WTT.ExpenseTrackingAppBE.exceptions.UserNameTakenException;

import com.WTT.ExpenseTrackingAppBE.repos.UserRepo;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
//Implementation of the UserService interface.
//This service handles user credential operations such as user creation.
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    @Override
    public UserDto createUser(PostNewUser postNewUser) {
        // Check if username is already taken
        Optional<User> possibleNameTaken =
                userRepo
                        .findByUserName(postNewUser
                                .userName()
                                .toLowerCase());
        if(possibleNameTaken.isPresent()){
            throw new UserNameTakenException("Username has been taken, Please choose another!");
        }

        // Prevent creation of ADMIN users
        if(postNewUser.role().equals("ADMIN")) {
            throw new InvalidRoleException("User cannot be created as ADMIN, has to be USER or DEALER");
        }

        // Create and save new user
        User user = new User(
                postNewUser.userName().toLowerCase(),
                passwordEncoder.encode(postNewUser.password()),
                postNewUser.role().toUpperCase()
        );
        user = userRepo.save(user);

        // Convert to DTO for return
        UserDto userDto = new UserDto(user.getUsername(), user.getRole());

        return userDto;
    }


    @Override
    public String login(AuthorizationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.userName(),
                request.password()
        ));
        String jwt = jwtService.generateToken(request.userName());
        return jwt;
    }

}