package com.happyhost.controller;

import com.happyhost.dto.LoginRequest;
import com.happyhost.dto.RegisterRequest;
import com.happyhost.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request){
        return userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        var response = userService.login(request);
        if (response == null){
            return new ResponseEntity<>("Invalid Email or Password", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
