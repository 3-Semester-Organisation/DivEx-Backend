package godevenner.divexbackend.portfolio.dto;

public record PortfolioEntryRequest(
    String ticker,
    int stockPrice,
    int quantity,

    long portfolioId
) {
}
