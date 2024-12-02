package godevenner.divexbackend.user.controller;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.user.dto.ChangeSubscriptionRequest;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/membership")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/upgrade")
    public ResponseEntity<AuthenticationResponse> changeSubscription(@RequestBody ChangeSubscriptionRequest changeSubscriptionRequest) {

        AuthenticationResponse authenticationResponse = userService.changeUserSubscription(changeSubscriptionRequest);

        return ResponseEntity.ok(authenticationResponse);

    }
}


