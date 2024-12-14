package godevenner.divexbackend.portfolio.dto;

public record DeletePortfolioEntryRequest(
        long portfolioEntryId,
        String stockTicker,
        long portfolioId
) {
}
