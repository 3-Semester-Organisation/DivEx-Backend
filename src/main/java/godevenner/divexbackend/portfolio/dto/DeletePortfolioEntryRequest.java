package godevenner.divexbackend.portfolio.dto;

public record DeletePortfolioEntryRequest(
        String portfolioStockTicker,
        long portfolioEntryId,
        long portfolioId
) {
}
