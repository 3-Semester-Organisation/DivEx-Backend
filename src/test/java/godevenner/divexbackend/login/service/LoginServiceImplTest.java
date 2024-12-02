package godevenner.divexbackend.login.service;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.RegisterRequest;
import godevenner.divexbackend.login.dto.ShortRegisterRequest;
import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.subscription.model.repository.SubscriptionRepository;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import godevenner.divexbackend.user.service.UserService;
import godevenner.divexbackend.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

class LoginServiceImplTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepository;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtService jwtService;
    @Mock
    SubscriptionRepository subscriptionRepository;

    @InjectMocks
    LoginServiceImpl loginServiceImpl;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodeResponse");
        when(userServiceImpl.save(any(User.class))).thenReturn(new User());
        when(jwtService.generateToken(any(Map.class), any(User.class))).thenReturn("generateTokenResponse");
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(new Subscription());

        AuthenticationResponse result = loginServiceImpl.register(new RegisterRequest("subscriptionType", "username", "email", "password", "firstName", "lastName", "phone", "address", "city"));
        Assertions.assertEquals(new AuthenticationResponse("generateTokenResponse"), result);
    }

    @Test
    void testShortRegister() {
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodeResponse");
        when(userServiceImpl.save(any(User.class))).thenReturn(new User());
        when(jwtService.generateToken(any(Map.class), any(UserDetails.class))).thenReturn("generateTokenResponse");
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(new Subscription());

        AuthenticationResponse result = loginServiceImpl.shortRegister(new ShortRegisterRequest("username", "email", "password"));
        Assertions.assertEquals(new AuthenticationResponse("generateTokenResponse"), result);
    }

    @Test
    void testLogin() {
        Subscription mockSubscription = new Subscription();
        User mockUser = new User();
        mockUser.setSubscription(mockSubscription);

        when(userServiceImpl.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(null);
        when(jwtService.generateToken(any(Map.class), any(User.class))).thenReturn("generateTokenResponse");

        AuthenticationResponse result = loginServiceImpl.login(new LoginRequest("username", "password"));
        Assertions.assertEquals(new AuthenticationResponse("generateTokenResponse"), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme