package godevenner.divexbackend.stock.dto;

import godevenner.divexbackend.stock.model.Currency;

import java.time.LocalDate;
import java.util.List;

public record StockResponse(
        String ticker,
        String name,
        String country,
        String exchange,
        Currency currency,
        String industry,
        String sector,

        List<HistoricalPricingResponse> historicalPricingResponseList,

        double dividendRate,
        double dividendYield,
        double dividendRatio,
        double fiveYearAvgDividendYield,
        LocalDate exDividendDate,

        List<HistoricalDividendsResponse> historicalDividendsResponseList
) {
}
