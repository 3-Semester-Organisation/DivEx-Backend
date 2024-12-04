package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.PortfolioResponse;
import godevenner.divexbackend.portfolio.mapper.PortfolioEntryMapper;
import godevenner.divexbackend.portfolio.mapper.PortfolioMapper;
import godevenner.divexbackend.portfolio.model.Portfolio;
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
        System.out.println(portfolioRepository.findByUserId(userId));
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
        System.out.println("portfolioname: " + portfolioName);
        portfolio.setName(portfolioName);
        portfolio.setUser(user.get());

        return portfolioRepository.save(portfolio);
    }
}
