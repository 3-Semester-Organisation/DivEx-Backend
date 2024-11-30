package godevenner.divexbackend.login.controller;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.ShortRegisterRequest;
import godevenner.divexbackend.login.service.LoginService;
import godevenner.divexbackend.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;

class LoginControllerTest {
    @Mock
    LoginService loginService;
    @Mock
    UserService userService;
    @InjectMocks
    LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        when(loginService.shortRegister(any(ShortRegisterRequest.class))).thenReturn(new AuthenticationResponse("jwt"));
        when(userService.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());

        ResponseEntity<AuthenticationResponse> result = loginController.register(new ShortRegisterRequest("username", "email", "password"));
        Assertions.assertEquals(new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse("jwt"), null, 200), result);
    }

    @Test
    void testLogin() {
        when(loginService.login(any(LoginRequest.class))).thenReturn(new AuthenticationResponse("jwt"));

        AuthenticationResponse result = loginController.login(new LoginRequest("username", "password"));
        Assertions.assertEquals(new AuthenticationResponse("jwt"), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme