package godevenner.divexbackend.subscription.model.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.user.dto.ChangeSubscriptionRequest;

public interface SubscriptionService {

    AuthenticationResponse changeSubscriptionById(ChangeSubscriptionRequest changeSubscriptionRequest);
}
