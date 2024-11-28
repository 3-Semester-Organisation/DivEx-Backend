package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.exception.InvalidTickerException;
import godevenner.divexbackend.stock.dto.HistoricalDividendsResponse;
import godevenner.divexbackend.stock.dto.HistoricalPricingResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.mapper.StockResponseMapper;
import godevenner.divexbackend.stock.model.*;
import godevenner.divexbackend.stock.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockResponseMapper stockResponseMapper;

    @InjectMocks
    private StockServiceImpl stockService;



    @Test
    void getAllStocksPagination() {

        //Apple Stock
        Dividend appleDividend = new Dividend(1L, 2.5, 1.2, 0.6, 1.1, 5000000);
        HistoricalPricing pricing1 = new HistoricalPricing(1L, 150.0,5000000, 148.0, 4000000);
        HistoricalDividend dividendHistory1 = new HistoricalDividend(1L, 2.0, 5000000);
        Stock appleStock = new Stock(1L, "AAPL", "Apple Inc.", "USA", "NASDAQ", Currency.DKK, "Technology", "Consumer Electronics", appleDividend, List.of(dividendHistory1), List.of(pricing1));

        //Google Stock
        Dividend goggleDividend = new Dividend(2L, 3.0, 1.5, 0.7, 1.3, 5000000);
        HistoricalPricing pricing2 = new HistoricalPricing(2L, 152.1, 5000000, 150.0, 4000000);
        HistoricalDividend dividendHistory2 = new HistoricalDividend(2L, 2.1, 5000000);
        Stock goggleStock = new Stock(2L, "GOOGL", "Alphabet Inc.", "USA", "NASDAQ", Currency.SEK, "Technology", "Internet Services", goggleDividend, List.of(dividendHistory2), List.of(pricing2));


        List<Stock> stockList = List.of(appleStock, goggleStock); // Example data
        Pageable pageable = PageRequest.of(0, 20); // Page 0 with 20 items per page
        long totalElements = stockList.size(); // Total number of elements (could be a database count)


        Page<Stock> stocks = new PageImpl<>(stockList, pageable, totalElements);
        Mockito.when(stockRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(stocks);


        // Apple StockResponse
        StockResponse appleStockResponse = new StockResponse(
                "AAPL",
                "Apple Inc.",
                "USA",
                "NASDAQ",
                Currency.DKK,
                "Technology",
                "Consumer Electronics",
                List.of(new HistoricalPricingResponse(150.0, 5000000, 148.0, 4000000)),
                2.5,
                1.2,
                0.6,
                1.1,
                5000000,
                List.of(new HistoricalDividendsResponse(2.0, 5000000))
        );

        // Google StockResponse
        StockResponse googleStockResponse = new StockResponse(
                "GOOGL",
                "Alphabet Inc.",
                "USA",
                "NASDAQ",
                Currency.SEK,
                "Technology",
                "Internet Services",
                List.of(new HistoricalPricingResponse(152.1, 5000000, 150.0, 4000000)),
                3.0,
                1.5,
                0.7,
                1.3,
                5000000,
                List.of(new HistoricalDividendsResponse(2.1, 5000000))
        );

        List<StockResponse> stockResponseList = Arrays.asList(appleStockResponse, googleStockResponse);

        Mockito.when(stockResponseMapper.toStockResponse(ArgumentMatchers.any(Stock.class))).thenReturn(appleStockResponse).thenReturn(googleStockResponse);

        Page<StockResponse> expectedResult = new PageImpl<>(stockResponseList, pageable, totalElements);
        Page<StockResponse> actualResult = stockService.getAllStocks(pageable);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getStockByTicker() {
        //Apple Stock
        Dividend appleDividend = new Dividend(1L, 2.5, 1.2, 0.6, 1.1, 5000000);
        HistoricalPricing pricing1 = new HistoricalPricing(1L, 150.0, 5000000, 148.0, 4000000);
        HistoricalDividend dividendHistory1 = new HistoricalDividend(1L, 2.0, 5000000);
        Stock appleStock = new Stock(1L, "AAPL", "Apple Inc.", "USA", "NASDAQ", Currency.DKK, "Technology", "Consumer Electronics", appleDividend, List.of(dividendHistory1), List.of(pricing1));
        Optional<Stock> stockOptional = Optional.of(appleStock);

        Mockito.when(stockRepository.findByTicker(ArgumentMatchers.anyString())).thenReturn(stockOptional);

        // Apple StockResponse
        StockResponse appleStockResponse = new StockResponse(
                "AAPL",
                "Apple Inc.",
                "USA",
                "NASDAQ",
                Currency.DKK,
                "Technology",
                "Consumer Electronics",
                List.of(new HistoricalPricingResponse(150.0, 5000000, 148.0, 4000000)),
                2.5,
                1.2,
                0.6,
                1.1,
                5000000,
                List.of(new HistoricalDividendsResponse(2.0, 5000000))
        );
        Mockito.when(stockResponseMapper.toStockResponse(ArgumentMatchers.any(Stock.class))).thenReturn(appleStockResponse);

        StockResponse expectedResult = appleStockResponse;
        StockResponse actualResult = stockService.getStock("APPL");

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getStockByTickerFail() {
        Mockito.when(stockRepository.findByTicker(ArgumentMatchers.anyString())).thenThrow(new InvalidTickerException("Invalid ticker: X"));
        assertThrows(InvalidTickerException.class, () -> stockService.getStock("X"));
    }
}