package godevenner.divexbackend.stock.dto;


public record HistoricalPricingResponse(
        double openingPrice,
        long openingDate,
        double previousDailyClosingPrice,
        long closingDate
) {
}
