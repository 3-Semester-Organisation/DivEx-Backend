package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.DeletePortfolioRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioResponse;
import godevenner.divexbackend.portfolio.dto.UpdatePortfolioGoalRequest;
import godevenner.divexbackend.portfolio.dto.UpdatePortfolioRequest;
import godevenner.divexbackend.portfolio.model.Portfolio;

import java.util.List;

public interface PortfolioService {

    List<PortfolioResponse> getPortfolios(Long userId);
    Portfolio createPortfolio(String portfolioName, Long userId);
    Portfolio updatePortfolio(UpdatePortfolioRequest request);
    PortfolioResponse updatePortfolioGoal(UpdatePortfolioGoalRequest request);

    void deletePortfolio(DeletePortfolioRequest request);

}
