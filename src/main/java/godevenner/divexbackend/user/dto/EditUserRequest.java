package godevenner.divexbackend.user.dto;

import godevenner.divexbackend.subscription.Subscription;

public record EditUserRequest(
        String username,
        String email,
        String firstName,
        String lastName,
        String phone
) {
}
