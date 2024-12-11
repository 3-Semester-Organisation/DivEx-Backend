package godevenner.divexbackend.portfolio.dto;

public record DeleteRequest(
        String ticker,
        long portfolioId
) {
}
