package godevenner.divexbackend.subscription.model.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.subscription.model.SubscriptionType;
import godevenner.divexbackend.subscription.model.repository.SubscriptionRepository;
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
    public AuthenticationResponse changeSubscriptionById(ChangeSubscriptionRequest changeSubscriptionRequest) {
        long subscriptionId = changeSubscriptionRequest.subscriptionId();
        SubscriptionType newSubscriptionType = changeSubscriptionRequest.newSubscriptionType();


        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();


        subscription.setSubscriptionType(newSubscriptionType);

        subscription.setLastPayment(LocalDateTime.now());

        subscription.setNextPayment(LocalDateTime.now().plusDays(30));


        subscriptionRepository.save(subscription);
        User user = userRepository.findById(subscriptionId).orElseThrow();

        return userService.generatedAuthenticationResponse(user);
    }
}
