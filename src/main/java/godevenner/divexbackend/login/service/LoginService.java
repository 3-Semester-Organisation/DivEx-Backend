package godevenner.divexbackend.login.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.RegisterRequest;

public interface LoginService {

    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);
}
