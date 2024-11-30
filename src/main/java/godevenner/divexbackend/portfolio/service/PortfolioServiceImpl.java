package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<Portfolio> getPortfolios(Integer userId) {
        return portfolioRepository.findByUserId(userId);
    }

    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }
}
