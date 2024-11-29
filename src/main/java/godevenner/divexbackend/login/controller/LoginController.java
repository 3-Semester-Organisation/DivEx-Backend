package godevenner.divexbackend.login.controller;


import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.RegisterRequest;
import godevenner.divexbackend.login.service.LoginService;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        Optional<User> isUsernameTaken = userService.findByUsername(registerRequest.username());
        if (isUsernameTaken.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        AuthenticationResponse authenticationResponse = loginService.register(registerRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = loginService.login(loginRequest);

        //TODO make optional that returns badRequest if login is inputted incorrectly

        return ResponseEntity.ok(authenticationResponse);
    }
}
