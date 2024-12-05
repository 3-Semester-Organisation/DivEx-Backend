package godevenner.divexbackend.user.service;

import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User save(User newUser);

    boolean maxNumberOfPortfoliosPrUserReached(Long userId);

    boolean maxNumberOfPortfolioEntriesPrPortfolioReached(Long portfolioId, Long userId);

    AuthenticationResponse generatedAuthenticationResponse(User user);
}
