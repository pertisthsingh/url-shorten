package org.pertisth.bitly.controllers;

import org.pertisth.bitly.dto.Error;
import org.pertisth.bitly.dto.RegisterDto;
import org.pertisth.bitly.models.User;
import org.pertisth.bitly.services.UserService;
import org.pertisth.bitly.services.impl.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthService authService;

    public AuthenticationController(UserService userService,AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            Optional<User> savedUser = userService.registerUser(user);
            if (savedUser.isPresent()) {
                return ResponseEntity.ok(new RegisterDto(user.getUsername(), "User Created"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Invalid Input"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.login(user));
    }
}