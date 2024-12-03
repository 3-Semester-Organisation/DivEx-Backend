package godevenner.divexbackend.subscription.model.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.subscription.model.service.SubscriptionService;
import godevenner.divexbackend.user.dto.ChangeSubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final JwtService jwtService;

    @PutMapping("/upgrade")
    public ResponseEntity<AuthenticationResponse> changeSubscription(@RequestBody ChangeSubscriptionRequest changeSubscriptionRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        long id = Long.parseLong(jwtService.extractUserId(token));

        AuthenticationResponse authenticationResponse = subscriptionService.changeSubscriptionById(id, changeSubscriptionRequest);

        return ResponseEntity.ok(authenticationResponse);

    }
}
