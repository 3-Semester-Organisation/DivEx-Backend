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
    void testCreatePortfolioEntry() {
        when(portfolioEntryRepository.save(any(PortfolioEntry.class))).thenReturn(new PortfolioEntry(Long.valueOf(1), 0d, 0, 0L, null, null));
        when(portfolioEntryMapper.toPortfolioEntry(any(PortfolioEntryRequest.class))).thenReturn(new PortfolioEntry(Long.valueOf(1), 0d, 0, 0L, null, null));
        when(portfolioEntryMapper.toPortfolioEntryResponse(any(PortfolioEntry.class))).thenReturn(new PortfolioEntryResponse(new StockResponse(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", List.of(new HistoricalPricingResponse(0d, 0L, 0d, 0L)), 0d, 0d, 0d, 0d, 0L, List.of(new HistoricalDividendsResponse(0d, 0L))), 0d, 0, 0L,1));

        PortfolioEntryResponse result = portfolioEntryServiceImpl.createPortfolioEntry(new PortfolioEntryRequest("ticker", 0d, 0, 0L));
        Assertions.assertEquals(new PortfolioEntryResponse(new StockResponse(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", List.of(new HistoricalPricingResponse(0d, 0L, 0d, 0L)), 0d, 0d, 0d, 0d, 0L, List.of(new HistoricalDividendsResponse(0d, 0L))), 0d, 0, 0L,1), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme