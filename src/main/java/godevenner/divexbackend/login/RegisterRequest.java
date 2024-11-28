package godevenner.divexbackend.login;

public record RegisterRequest(
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
