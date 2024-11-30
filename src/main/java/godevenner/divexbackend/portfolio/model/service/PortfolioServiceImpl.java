package godevenner.divexbackend.portfolio.model.service;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.repository.PortfolioRepository;
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
}
