package godevenner.divexbackend.portfolio.dto;

public record UpdatePortfolioRequest(
        String portfolioName,
        long portfolioId
) {
}
