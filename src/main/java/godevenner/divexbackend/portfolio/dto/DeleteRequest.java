package godevenner.divexbackend.portfolio.dto;

public record DeleteRequest(
        String portfolioStockTicker,
        long portfolioEntryId,
        long portfolioId
) {
}
