package com.happyhost.service;

import com.happyhost.dto.LoginRequest;
import com.happyhost.dto.RegisterRequest;
import com.happyhost.model.User;
import com.happyhost.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testRegister() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());
        var response = userService.register(new RegisterRequest());
        assertEquals("User registered successfully", response);
    }

    @Test
    void testRegisterEmailAlreadyExist() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@gmail.com");

        User existingUser = new User();
        existingUser.setEmail("test@gmail.com");

        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(existingUser));
        String response = userService.register(request);
        assertEquals("Email already registered", response);
    }

    @Test
    void testLoginSuccess() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("password123");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password123");

        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        User loggedInUser = userService.login(request);
        assertNotNull(loggedInUser);
        assertEquals("test@gmail.com", loggedInUser.getEmail());
    }

    @Test
    void testLoginInvalidPassword() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("correctPassword");

        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        User result = userService.login(request);
        assertNull(result);
    }

    @Test
    void testLoginInvalidEmail() {
        LoginRequest request = new LoginRequest();
        request.setEmail("notfound@gmail.com");
        request.setPassword("anyPassword");

        Mockito.when(userRepository.findByEmail("notfound@gmail.com"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.login(request);
        });
        assertEquals("Invalid email or password", exception.getMessage());
    }


}
