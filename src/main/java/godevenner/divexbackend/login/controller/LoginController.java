package godevenner.divexbackend.login.controller;


import godevenner.divexbackend.exception.EmailTakenException;
import godevenner.divexbackend.exception.UsernameTakenException;
import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.RegisterRequest;
import godevenner.divexbackend.login.dto.ShortRegisterRequest;
import godevenner.divexbackend.login.service.LoginService;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody ShortRegisterRequest registerRequest) {

        Optional<User> isUsernameTaken = userService.findByUsername(registerRequest.username());
        if (isUsernameTaken.isPresent()) {
            throw new UsernameTakenException("Username already taken.");
        }
        Optional<User> isEmailTaken = userService.findByEmail(registerRequest.email());
        if (isEmailTaken.isPresent()) {
            throw new EmailTakenException("Email already taken.");
        }

        AuthenticationResponse authenticationResponse = loginService.shortRegister(registerRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
