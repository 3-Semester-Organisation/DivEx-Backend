package godevenner.divexbackend.stock.dto;

import java.time.LocalDate;

public record HistoricalDividendsResponse(
        double dividendRate,
        LocalDate exDividendDate
) {
}
