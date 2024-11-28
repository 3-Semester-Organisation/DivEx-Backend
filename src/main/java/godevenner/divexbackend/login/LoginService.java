package godevenner.divexbackend.login;

public interface LoginService {

    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);
}
