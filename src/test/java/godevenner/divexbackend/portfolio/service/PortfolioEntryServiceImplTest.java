package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.stock.model.*;
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
    @InjectMocks
    PortfolioEntryServiceImpl portfolioEntryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePortfolioEntry() {
        when(portfolioEntryRepository.save(any(PortfolioEntry.class))).thenReturn(new PortfolioEntry(Long.valueOf(1), 0, new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))));

        PortfolioEntry result = portfolioEntryServiceImpl.createPortfolioEntry(new PortfolioEntry(Long.valueOf(1), 0, new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))));
        Assertions.assertEquals(new PortfolioEntry(Long.valueOf(1), 0, new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme