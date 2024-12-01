package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    public List<Portfolio> getPortfolios(Integer userId) {
        return portfolioRepository.findByUserId(userId);
    }

    public Portfolio createPortfolio(String portfolioName, Long userId) {
        Portfolio portfolio = new Portfolio();
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        portfolio.setName(portfolioName);
        portfolio.setUser(user.get());

        return portfolioRepository.save(portfolio);
    }
}
