package godevenner.divexbackend.portfolio.dto;

public record UpdatePortfolioGoalRequest(
        Long portfolioId,
        int goal
) {
}
