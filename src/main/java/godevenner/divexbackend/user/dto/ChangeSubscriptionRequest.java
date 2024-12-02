package godevenner.divexbackend.user.dto;


import godevenner.divexbackend.subscription.model.SubscriptionType;

public record ChangeSubscriptionRequest(
        String username,
        SubscriptionType newSubscriptionType,
        int lengthOfNewSubscriptionInDays
) {
}
