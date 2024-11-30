package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.model.Portfolio;

import java.util.List;

public interface PortfolioService {

    List<Portfolio> getPortfolios(Integer userId);
    Portfolio createPortfolio(Portfolio portfolio);


}
