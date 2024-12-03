package godevenner.divexbackend.user.dto;


import godevenner.divexbackend.subscription.SubscriptionType;

public record ChangeSubscriptionRequest(
        SubscriptionType newSubscriptionType
) {
}
