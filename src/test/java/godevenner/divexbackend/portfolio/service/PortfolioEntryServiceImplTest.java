package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.mapper.PortfolioEntryMapper;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.stock.dto.HistoricalDividendsResponse;
import godevenner.divexbackend.stock.dto.HistoricalPricingResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class PortfolioEntryServiceImplTest {
    @Mock
    PortfolioEntryRepository portfolioEntryRepository;
    @Mock
    PortfolioEntryMapper portfolioEntryMapper;
    @InjectMocks
    PortfolioEntryServiceImpl portfolioEntryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolioEntries() {
        when(portfolioEntryRepository.findByPortfolioId(anyLong())).thenReturn(List.of(new PortfolioEntry(1L,
                new Portfolio(0L, "portfolioName",
                        new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15), LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15)))),
                new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector",
                        new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                        List.of(new HistoricalDividend(0L, 0d, 0L)),
                        List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))));

        List<PortfolioEntry> result = portfolioEntryServiceImpl.getPortfolioEntries(0L);
        Assertions.assertEquals(List.of(new PortfolioEntry(1L,
                new Portfolio(0L, "portfolioName", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15), LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15)))),
                new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector",
                        new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                        List.of(new HistoricalDividend(0L, 0d, 0L)),
                        List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))), result);
    }

    @Test
    void testCreatePortfolioEntry() {
        when(portfolioEntryRepository.save(any(PortfolioEntry.class))).thenReturn(new PortfolioEntry(Long.valueOf(1), 0, 0, 0L, null));
        when(portfolioEntryMapper.toPortfolioEntry(any(PortfolioEntryRequest.class))).thenReturn(new PortfolioEntry(Long.valueOf(1), 0, 0, 0L, null));
        when(portfolioEntryMapper.toPortfolioEntryResponse(any(PortfolioEntry.class))).thenReturn(new PortfolioEntryResponse(new StockResponse("ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", List.of(new HistoricalPricingResponse(0d, 0L, 0d, 0L)), 0d, 0d, 0d, 0d, 0L, List.of(new HistoricalDividendsResponse(0d, 0L))), 0, 0, 0L));

        PortfolioEntryResponse result = portfolioEntryServiceImpl.createPortfolioEntry(new PortfolioEntryRequest("ticker", 0, 0));
        Assertions.assertEquals(new PortfolioEntryResponse(new StockResponse("ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", List.of(new HistoricalPricingResponse(0d, 0L, 0d, 0L)), 0d, 0d, 0d, 0d, 0L, List.of(new HistoricalDividendsResponse(0d, 0L))), 0, 0, 0L), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme