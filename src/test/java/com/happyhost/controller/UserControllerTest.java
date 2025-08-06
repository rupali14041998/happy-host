package com.happyhost.controller;

import com.happyhost.dto.LoginRequest;
import com.happyhost.dto.RegisterRequest;
import com.happyhost.model.User;
import com.happyhost.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void testRegister() {
        Mockito.when(userService.register(Mockito.any())).thenReturn("User registered successfully");
        var response = userController.register(new RegisterRequest());
        assertEquals("User registered successfully", response);
    }

    @Test
    void testSuccessLogin() {
        Mockito.when(userService.login(Mockito.any())).thenReturn(new User());
        var response = userController.login(new LoginRequest());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testLoginFailed() {
        Mockito.when(userService.login(Mockito.any())).thenReturn(null);
        var respoonse = userController.login(new LoginRequest());
        assertEquals(401, respoonse.getStatusCode().value());
    }
}
