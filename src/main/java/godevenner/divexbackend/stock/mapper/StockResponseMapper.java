package godevenner.divexbackend.stock.mapper;

import godevenner.divexbackend.stock.dto.HistoricalDividendsResponse;
import godevenner.divexbackend.stock.dto.HistoricalPricingResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.Dividend;
import godevenner.divexbackend.stock.model.Stock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockResponseMapper {

    public StockResponse toStockResponse(Stock stock) {
        Dividend dividend = stock.getDividend();

        return new StockResponse(
                stock.getId(),
                stock.getTicker(),
                stock.getName(),
                stock.getCountry(),
                stock.getExchange(),
                stock.getCurrency(),
                stock.getIndustry(),
                stock.getSector(),

                toHistoricalPricingResponse(stock),

                dividend.getDividendRate(),
                dividend.getDividendYield(),
                dividend.getDividendRatio(),
                dividend.getFiveYearAvgDividendYield(),
                dividend.getExDividendDate(),

                toHistoricalDividendsResponse(stock)
        );


    }

    private List<HistoricalPricingResponse> toHistoricalPricingResponse(Stock stock) {
        return stock.getHistoricalPricings().stream()
                .map(historicalPricing -> new HistoricalPricingResponse(
                        historicalPricing.getOpeningPrice(),
                        historicalPricing.getOpeningDate(),
                        historicalPricing.getPreviousDailyClosingPrice(),
                        historicalPricing.getClosingDate()
                )).toList();
    }

    private List<HistoricalDividendsResponse> toHistoricalDividendsResponse(Stock stock) {
        return stock.getHistoricalDividends().stream()
                .map(historicalDividend -> new HistoricalDividendsResponse(
                        historicalDividend.getDividend(),
                        historicalDividend.getExDividendDate()
                )).toList();
    }
}
