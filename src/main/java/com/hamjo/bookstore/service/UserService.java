package com.hamjo.bookstore.service;

import com.hamjo.bookstore.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User register(RegisterUserRequest registerUserRequest) {
        if (userExists(registerUserRequest.getEmail())) {
            throw new InvalidArgumentException("An account already exists with this email");
        }

        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setEmailVerified(0);

        return userRepository.save(user);
    }

    public User getUser(){}
}
