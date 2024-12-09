package godevenner.divexbackend.user.service;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.login.dto.AuthenticationResponse;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import godevenner.divexbackend.subscription.Subscription;
import godevenner.divexbackend.subscription.SubscriptionType;
import godevenner.divexbackend.subscription.repository.SubscriptionRepository;
import godevenner.divexbackend.user.dto.EditUserRequest;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final PortfolioEntryRepository portfolioEntryRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${max.number.of.portfolios.free}")
    private int maxNumberOfPortfoliosFree;
    @Value("${max.number.of.portfolioentries.free}")
    private int maxNumberOfPortfolioEntriesFree;


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public boolean maxNumberOfPortfoliosPrUserReached(Long userId) {

        Subscription subscription = subscriptionRepository.findById(userId).orElseThrow();
        if (subscription.getSubscriptionType() == SubscriptionType.PREMIUM) return false;

        int numberOfPortfolios = portfolioRepository.findByUserId(userId).size();
        return numberOfPortfolios >= maxNumberOfPortfoliosFree; // hvis numberOfPortfolios er over eller lig maxNumberOfPortfoliosFree - return true
    }

    @Override
    public boolean maxNumberOfPortfolioEntriesPrPortfolioReached(Long portfolioId, Long userId) {

        Subscription subscription = subscriptionRepository.findById(userId).orElseThrow();
        if (subscription.getSubscriptionType() == SubscriptionType.PREMIUM) return false;

        int numberOfPortfolioEntries = portfolioEntryRepository.findByPortfolioId(portfolioId).size();
        return numberOfPortfolioEntries >= maxNumberOfPortfolioEntriesFree; // hvis numberOfPortfolioEntries er over eller lig maxNumberOfPortfolioEntriesFree - return true
    }


    @Override
    public AuthenticationResponse generatedAuthenticationResponse(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("subscriptionType", user.getSubscription().getSubscriptionType());
        extraClaims.put("userId", user.getId());
        String token = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(token);
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void changePassword(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void editUser(Long id, EditUserRequest editUserRequest) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(editUserRequest.firstName());
        user.setLastName(editUserRequest.lastName());
        user.setEmail(editUserRequest.email());
        user.setPhone(editUserRequest.phone());
        userRepository.save(user);
    }


}
