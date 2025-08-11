package org.pertisth.bitly.services;

import org.pertisth.bitly.dto.LoginResponseDto;
import org.pertisth.bitly.models.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface UserService {
    Optional<User> registerUser(@RequestBody User user);
    LoginResponseDto login(User user);
}
