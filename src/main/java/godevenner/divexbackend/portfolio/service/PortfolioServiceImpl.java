package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.DeletePortfolioRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioResponse;
import godevenner.divexbackend.portfolio.dto.UpdatePortfolioGoalRequest;
import godevenner.divexbackend.portfolio.mapper.PortfolioEntryMapper;
import godevenner.divexbackend.portfolio.mapper.PortfolioMapper;
import godevenner.divexbackend.portfolio.dto.UpdatePortfolioRequest;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;
    private final UserRepository userRepository;

    public List<PortfolioResponse> getPortfolios(Long userId) {
        List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);

        List<PortfolioResponse> response = portfolios.stream()
                .map(portfolioMapper::toPortfolioResponse)
                .toList();
        return response;
    }

    public Portfolio createPortfolio(String portfolioName, Long userId) {
        Portfolio portfolio = new Portfolio();
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        portfolio.setName(portfolioName);
        portfolio.setUser(user.get());
        portfolio.setPortfolioEntries(new ArrayList<>());

        return portfolioRepository.save(portfolio);
    }

    public Portfolio updatePortfolio(UpdatePortfolioRequest request) {
        Portfolio portfolio = portfolioRepository.findById(request.portfolioId()).orElseThrow( () -> new RuntimeException("Portfolio not found"));
        portfolio.setName(request.portfolioName());

        return portfolioRepository.save(portfolio);
    }

    public Portfolio updatePortfolioGoal(UpdatePortfolioGoalRequest request) {
        Portfolio portfolio = portfolioRepository.findById(request.portfolioId()).orElseThrow();
        portfolio.setGoal(request.goal());
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    public void deletePortfolio(DeletePortfolioRequest request) {
        portfolioRepository.deleteById(request.portfolioId());
    }
}
