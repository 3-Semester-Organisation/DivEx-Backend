package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.portfolio.dto.CreatePortfolioRequest;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.service.PortfolioEntryService;
import godevenner.divexbackend.portfolio.service.PortfolioService;
import godevenner.divexbackend.stock.model.*;
import godevenner.divexbackend.subscription.Subscription;
import godevenner.divexbackend.subscription.SubscriptionType;
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
    @Mock
    JwtService jwtService;
    @InjectMocks
    PortfolioController portfolioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolios() {
        String authorizationHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWJzY3JpcHRpb25UeXBlIjoiUFJFTUlVTSIsInVzZXJJZCI6IjEifQ.twet1zk49wC3VCEy6E3VHG9NAQ-bWyJpDcjOGeorWhw";
        when(portfolioService.getPortfolios(anyLong())).thenReturn(List.of(new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18))))));
        when(jwtService.extractUserId(anyString())).thenReturn("1");

        ResponseEntity<List<Portfolio>> result = portfolioController.getPortfolios(authorizationHeader);
        Assertions.assertEquals(new ResponseEntity<List<Portfolio>>(List.of(new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18))))), null, 200), result);
    }

    @Test
    void testCreatePortfolio() {
        when(portfolioService.createPortfolio(anyString(), anyLong())).thenReturn(new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))));
        when(jwtService.extractUserId(anyString())).thenReturn("1");

        ResponseEntity<Portfolio> result = portfolioController.createPortfolio(new CreatePortfolioRequest("portfolioName"), "authorizationHeader");
        Assertions.assertEquals(new ResponseEntity<Portfolio>(new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))), null, 200), result);
    }

    @Test
    void testGetPortfolioEntries() {
        when(portfolioEntryService.getPortfolioEntries(anyInt())).thenReturn(List.of(new PortfolioEntry(Long.valueOf(1), new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))), new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))));

        ResponseEntity<List<PortfolioEntry>> result = portfolioController.getPortfolioEntries(Integer.valueOf(0));
        Assertions.assertEquals(new ResponseEntity<List<PortfolioEntry>>(List.of(new PortfolioEntry(Long.valueOf(1), new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))), new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L))))), null, 200), result);
    }

    @Test
    void testCreatePortfolioEntry() {
        when(portfolioEntryService.createPortfolioEntry(any(PortfolioEntry.class))).thenReturn(new PortfolioEntry(Long.valueOf(1), new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))), new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))));

        ResponseEntity<PortfolioEntry> result = portfolioController.createPortfolioEntry(new PortfolioEntry(Long.valueOf(1), new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))), new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))));
        Assertions.assertEquals(new ResponseEntity<PortfolioEntry>(new PortfolioEntry(Long.valueOf(1), new Portfolio(0L, "portfolioName", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18), LocalDateTime.of(2024, Month.DECEMBER, 1, 17, 19, 18)))), new Stock(0L, "ticker", "portfolioName", "country", "exchange", Currency.DKK, "industry", "sector", new Dividend(0L, 0d, 0d, 0d, 0d, 0L), List.of(new HistoricalDividend(0L, 0d, 0L)), List.of(new HistoricalPricing(0L, 0d, 0L, 0d, 0L)))), null, 200), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme