package com.happyhost.service;

import com.happyhost.dto.LoginRequest;
import com.happyhost.dto.RegisterRequest;
import com.happyhost.model.User;
import com.happyhost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest registerRequest) {
        var userData = userRepository.findByEmail(registerRequest.getEmail());
        if (userData.isPresent()) {
            return "Email already registered";
        }

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setMobile(registerRequest.getMobile());
        user.setGender(registerRequest.getGender());
        user.setPassword(registerRequest.getPassword());

        userRepository.save(user);
        return "User registered successfully";
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (user.getPassword().equals(request.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
