package godevenner.divexbackend.user.service;

import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);



}
