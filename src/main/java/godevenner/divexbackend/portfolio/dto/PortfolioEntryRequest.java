package godevenner.divexbackend.portfolio.dto;

public record PortfolioEntryRequest(
    String ticker,
    double stockPrice,
    int quantity,

    long portfolioId
) {
}
