package com.WTT.ExpenseTrackingAppBE.services.user;

import com.WTT.ExpenseTrackingAppBE.config.JwtService;
import com.WTT.ExpenseTrackingAppBE.dto.AuthorizationRequest;
import com.WTT.ExpenseTrackingAppBE.dto.PostNewUser;
import com.WTT.ExpenseTrackingAppBE.dto.UserDto;
import com.WTT.ExpenseTrackingAppBE.entities.User;

import com.WTT.ExpenseTrackingAppBE.exceptions.UserNameTakenException;
import com.WTT.ExpenseTrackingAppBE.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDto registerUser(PostNewUser postNewUser) {
        // Check if username is already taken
        Optional<User> possibleNameTaken = userRepo.findByUserName(postNewUser.userName().toLowerCase());
        if (possibleNameTaken.isPresent()) {
            throw new UserNameTakenException("Username has been taken, please choose another!");
        }



        // Create and save new user
        User user = new User();
        user.setUserName(postNewUser.userName().toLowerCase());
        user.setPassword(passwordEncoder.encode(postNewUser.password())); // Encode password
        user.setEmail(postNewUser.email());
        user = userRepo.save(user);

        // Convert to DTO for return
        return new UserDto(user.getEmail(), user.getUsername(), user.getPassword());
    }

    @Override
    public String login(AuthorizationRequest request) {
        // Authenticate user credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );

        // Load user details
        User user = userRepo.findByUserName(request.userName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        return jwtService.generateToken(user.getUsername()); // Pass username or user object
    }


}