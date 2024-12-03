package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.UpdatePortfolioRequest;
import godevenner.divexbackend.portfolio.model.Portfolio;

import java.util.List;

public interface PortfolioService {

    List<Portfolio> getPortfolios(Long userId);
    Portfolio createPortfolio(String portfolioName, Long userId);
    Portfolio updatePortfolio(UpdatePortfolioRequest request);


}
