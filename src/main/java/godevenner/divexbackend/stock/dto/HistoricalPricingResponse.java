package godevenner.divexbackend.stock.dto;

import java.time.LocalDate;

public record HistoricalPricingResponse(
        double openingPrice,
        LocalDate openingDate,
        double previousDailyClosingPrice,
        LocalDate closingDate
) {
}
