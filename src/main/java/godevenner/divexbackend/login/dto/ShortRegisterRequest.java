package godevenner.divexbackend.login.dto;

public record ShortRegisterRequest(
        String username,
        String email,
        String password
) {
}
