package godevenner.divexbackend.subscription.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.subscription.Subscription;
import godevenner.divexbackend.subscription.SubscriptionType;
import godevenner.divexbackend.subscription.repository.SubscriptionRepository;
import godevenner.divexbackend.user.dto.ChangeSubscriptionRequest;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public AuthenticationResponse changeSubscriptionById(long id, ChangeSubscriptionRequest changeSubscriptionRequest) {
        SubscriptionType newSubscriptionType = changeSubscriptionRequest.newSubscriptionType();


        Subscription subscription = subscriptionRepository.findById(id).orElseThrow();


        subscription.setSubscriptionType(newSubscriptionType);

        subscription.setLastPayment(LocalDateTime.now());

        subscription.setNextPayment(LocalDateTime.now().plusDays(30));


        subscriptionRepository.save(subscription);
        User user = userRepository.findById(id).orElseThrow();

        return userService.generatedAuthenticationResponse(user);
    }
}
