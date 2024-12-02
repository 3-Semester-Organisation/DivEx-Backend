package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import godevenner.divexbackend.stock.model.*;
import godevenner.divexbackend.subscription.model.Subscription;
import godevenner.divexbackend.subscription.model.SubscriptionType;
import godevenner.divexbackend.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class PortfolioEntryServiceImplTest {
    @Mock
    PortfolioEntryRepository portfolioEntryRepository;
    @Mock
    PortfolioRepository portfolioRepository;
    @InjectMocks
    PortfolioEntryServiceImpl portfolioEntryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolioEntries() {
        when(portfolioEntryRepository.findByPortfolioId(anyInt())).thenReturn(List.of(new PortfolioEntry(1L,
                new Portfolio(0L, "portfolioName",
                        new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15), LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15)))),
                new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector",
                        new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                        List.of(new HistoricalDividend(0L, 0d, 0L)),
                        List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))));

        List<PortfolioEntry> result = portfolioEntryServiceImpl.getPortfolioEntries(0);
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
        PortfolioEntry mockPortfolioEntry = new PortfolioEntry(1L,
                new Portfolio(0L, "portfolioName",
                        new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15), LocalDateTime.of(2024, Month.NOVEMBER, 30, 23, 17, 15))
                        )
                ),
                new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector",
                        new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                        List.of(new HistoricalDividend(0L, 0d, 0L)),
                        List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))
                )
        );

        when(portfolioEntryRepository.save(any(PortfolioEntry.class))).thenReturn(mockPortfolioEntry);

        PortfolioEntry result = portfolioEntryServiceImpl.createPortfolioEntry(mockPortfolioEntry);
        Assertions.assertEquals(mockPortfolioEntry, result);
    }
}

