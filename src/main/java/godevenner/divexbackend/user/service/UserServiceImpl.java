package godevenner.divexbackend.user.service;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.subscription.model.SubscriptionType;
import godevenner.divexbackend.user.dto.ChangeSubscriptionRequest;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User newUser) {
        userRepository.save(newUser);
    }

    @Override
    public AuthenticationResponse changeUserSubscription(ChangeSubscriptionRequest changeSubscriptionRequest) {
        String username = changeSubscriptionRequest.username();
        SubscriptionType newSubscriptionType = changeSubscriptionRequest.newSubscriptionType();
        int lengthOfNewSubscriptionInDays = changeSubscriptionRequest.lengthOfNewSubscriptionInDays();


        User user = userRepository.findByUsername(username).orElseThrow();

        Subscription subscription = user.getSubscription();

        subscription.setSubscriptionType(newSubscriptionType);

        subscription.setLastPayment(LocalDateTime.now());

        // Beregn næste betalingstidspunkt
        LocalDateTime nextPayment = subscription.getNextPayment();
        if (nextPayment == null) {
            subscription.setNextPayment(LocalDateTime.now().plusDays(lengthOfNewSubscriptionInDays));
        } else {
            subscription.setNextPayment(nextPayment.plusDays(lengthOfNewSubscriptionInDays));
        }

        userRepository.save(user);

        return generatedAuthenticationResponse(user);
    }

    @Override
    public AuthenticationResponse generatedAuthenticationResponse(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("subscriptionType", user.getSubscription().getSubscriptionType());
        extraClaims.put("userId", user.getId());
        String token = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(token);
    }


}
