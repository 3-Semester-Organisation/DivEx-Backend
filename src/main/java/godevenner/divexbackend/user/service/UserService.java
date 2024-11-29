package godevenner.divexbackend.user.service;

import godevenner.divexbackend.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
