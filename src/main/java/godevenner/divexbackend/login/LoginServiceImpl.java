package godevenner.divexbackend.login;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.subscription.model.SubscriptionType;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {

        User newUser = User.builder()
                .subscription(
                        Subscription.builder()
                                .subscriptionType(SubscriptionType.FREE)
                                .build()
                )
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .phone(registerRequest.phone())
                .address(registerRequest.address())
                .city(registerRequest.city())
                .build();

        userRepository.save(newUser);

        return generatedAuthenticationResponse(newUser);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow();

        return generatedAuthenticationResponse(user);
    }

    private AuthenticationResponse generatedAuthenticationResponse(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("subscriptionType", user.getSubscription().getSubscriptionType());
        String token = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(token);
    }
}
