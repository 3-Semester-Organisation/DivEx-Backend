package godevenner.divexbackend.stock.dto;

import godevenner.divexbackend.stock.model.Currency;

import java.util.List;

public record StockResponse(
        long stockId,
        String ticker,
        String name,
        String country,
        String exchange,
        Currency currency,
        String industry,
        String sector,

        List<HistoricalPricingResponse> historicalPricing,

        double dividendRate,
        double dividendYield,
        double dividendRatio,
        double fiveYearAvgDividendYield,
        long exDividendDate,

        List<HistoricalDividendsResponse> historicalDividends
) {
}
