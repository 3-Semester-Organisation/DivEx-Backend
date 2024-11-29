package godevenner.divexbackend.login.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.login.dto.LoginRequest;
import godevenner.divexbackend.login.dto.RegisterRequest;
import godevenner.divexbackend.login.dto.ShortRegisterRequest;

public interface LoginService {

    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse shortRegister(ShortRegisterRequest shortRegisterRequest);
}
