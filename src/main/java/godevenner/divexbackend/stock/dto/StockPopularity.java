package godevenner.divexbackend.stock.dto;

public record StockPopularity(
        long stockId,
        String name,
        String ticker,
        int visits
) {
}
