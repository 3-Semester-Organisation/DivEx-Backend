package godevenner.divexbackend.subscription.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.user.dto.ChangeSubscriptionRequest;

public interface SubscriptionService {

    AuthenticationResponse changeSubscriptionById(Long id, ChangeSubscriptionRequest changeSubscriptionRequest);
}
