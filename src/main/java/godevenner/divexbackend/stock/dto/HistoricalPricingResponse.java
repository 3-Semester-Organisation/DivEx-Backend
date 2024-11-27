package godevenner.divexbackend.stock.dto;

import java.time.LocalDate;

public record HistoricalPricingResponse(
        double openingPrice,
        long openingDate,
        double previousDailyClosingPrice,
        long closingDate
) {
}
