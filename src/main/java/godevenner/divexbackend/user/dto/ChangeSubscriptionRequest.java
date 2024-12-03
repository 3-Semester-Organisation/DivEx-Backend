package godevenner.divexbackend.user.dto;


import godevenner.divexbackend.subscription.model.SubscriptionType;

public record ChangeSubscriptionRequest(
        long subscriptionId,
        SubscriptionType newSubscriptionType
) {
}
