package godevenner.divexbackend.login.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.RegisterRequest;
import godevenner.divexbackend.login.dto.ShortRegisterRequest;
import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.subscription.model.SubscriptionType;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.service.UserService;
import godevenner.divexbackend.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


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

        userService.save(newUser);

        return userService.generatedAuthenticationResponse(newUser);
    }


    public AuthenticationResponse shortRegister(ShortRegisterRequest shortRegisterRequest) {

        /*
        Subscription subscription = Subscription.builder()
                .subscriptionType(SubscriptionType.FREE)
                .build();
        subscriptionRepository.save(subscription);


         */


        User newUser = User.builder()
                .subscription(
                        Subscription.builder()
                                .subscriptionType(SubscriptionType.FREE)
                                .build()
                )
                .username(shortRegisterRequest.username())
                .email(shortRegisterRequest.email())
                .password(passwordEncoder.encode(shortRegisterRequest.password()))
                .build();

        userService.save(newUser);

        return userService.generatedAuthenticationResponse(newUser);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password()
                    )
            );
        } catch (AuthenticationException e) {
            // Throw a custom exception
            throw new BadCredentialsException("Invalid username or password", e);
        }

        User user = userService.findByUsername(loginRequest.username())
                .orElseThrow();

        return userService.generatedAuthenticationResponse(user);
    }


}
