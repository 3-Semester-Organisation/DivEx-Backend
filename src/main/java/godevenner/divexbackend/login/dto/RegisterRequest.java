package godevenner.divexbackend.login.dto;

public record RegisterRequest(
        String subscriptionType,
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String phone,
        String address,
        String city
) {
}
