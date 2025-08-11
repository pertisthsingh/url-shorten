package org.pertisth.bitly.services.impl;

import lombok.AllArgsConstructor;
import org.pertisth.bitly.dto.LoginResponseDto;
import org.pertisth.bitly.models.User;
import org.pertisth.bitly.repository.UserRepository;
import org.pertisth.bitly.security.AuthUtil;
import org.pertisth.bitly.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthService implements UserService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> registerUser(User user) {
        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is null or empty");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty");
        }
        if (userRepo.getUsersByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return Optional.ofNullable(savedUser);
    }

    public LoginResponseDto login(User user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (auth.isAuthenticated()) {
            User authUser = (User) auth.getPrincipal();
            String token = authUtil.generateToken(authUser);
            return new LoginResponseDto(token, authUser.getUsername(), "success");
        }
        return new LoginResponseDto("","","failure");
    }

}
