package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.service.PortfolioEntryService;
import godevenner.divexbackend.portfolio.service.PortfolioService;
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
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class PortfolioControllerTest {
    @Mock
    PortfolioService portfolioService;
    @Mock
    PortfolioEntryService portfolioEntryService;
    @InjectMocks
    PortfolioController portfolioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolios() {
        when(portfolioService.getPortfolios(anyInt())).thenReturn(
                List.of(new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13))))));

        ResponseEntity<List<Portfolio>> result = portfolioController.getPortfolios(1);
        Assertions.assertEquals(new ResponseEntity<>(
                List.of(new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13))))), null, 200), result);
    }

    @Test
    void testCreatePortfolio() {
        when(portfolioService.createPortfolio(any(Portfolio.class))).thenReturn(
                new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))));

        ResponseEntity<Portfolio> result = portfolioController.createPortfolio(
                new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))));
        Assertions.assertEquals(new ResponseEntity<>(
                new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))), null, 200), result);
    }

    @Test
    void testGetPortfolioEntries() {
        when(portfolioEntryService.getPortfolioEntries(anyInt())).thenReturn(List.of(
                new PortfolioEntry(1L,
                        new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))),
                        new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector",
                                new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                                List.of(new HistoricalDividend(0L, 0d, 0L)),
                                List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))));

        ResponseEntity<List<PortfolioEntry>> result = portfolioController.getPortfolioEntries(0);
        Assertions.assertEquals(new ResponseEntity<>(
                List.of(new PortfolioEntry(1L,
                        new Portfolio(0L, "name", new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))),
                        new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                                List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))), null, 200), result);
    }

    @Test
    void testCreatePortfolioEntry() {
        when(portfolioEntryService.createPortfolioEntry(any(PortfolioEntry.class))).thenReturn(
                new PortfolioEntry(1L,
                        new Portfolio(0L, "name",
                                new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))),
                        new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector",
                                new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                                List.of(new HistoricalDividend(0L, 0d, 0L)),
                                List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))));

        ResponseEntity<PortfolioEntry> result = portfolioController.createPortfolioEntry(
                new PortfolioEntry(1L,
                        new Portfolio(0L, "name",
                                new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))),
                        new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector",
                                new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                                List.of(new HistoricalDividend(0L, 0d, 0L)),
                                List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))));
        Assertions.assertEquals(new ResponseEntity<>(
                new PortfolioEntry(1L,
                        new Portfolio(0L, "name",
                                new User(1L, "username", "email", "password", "firstName", "lastName", "phone", "address", "city",
                                        new Subscription(1L, true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13), LocalDateTime.of(2024, Month.NOVEMBER, 30, 22, 59, 13)))),
                        new Stock(0L, "ticker", "name", "country", "exchange", Currency.DKK, "industry", "sector",
                                new Dividend(0L, 0d, 0d, 0d, 0d, 0L),
                                List.of(new HistoricalDividend(0L, 0d, 0L)),
                                List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))), null, 200), result);
    }
}