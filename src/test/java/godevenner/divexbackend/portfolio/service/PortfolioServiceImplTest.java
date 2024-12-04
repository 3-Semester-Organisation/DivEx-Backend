package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.UpdatePortfolioRequest;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import godevenner.divexbackend.subscription.Subscription;
import godevenner.divexbackend.subscription.SubscriptionType;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class PortfolioServiceImplTest {
    @Mock
    PortfolioRepository portfolioRepository;
    @InjectMocks
    PortfolioServiceImpl portfolioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolios() {
        when(portfolioRepository.findByUserId(anyLong())).thenReturn(List.of(new Portfolio(0L, "name", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55), LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55))))));

        List<Portfolio> result = portfolioServiceImpl.getPortfolios(Long.valueOf(1));
        Assertions.assertEquals(List.of(new Portfolio(0L, "name", new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55), LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55))))), result);
    }

    @Test
    void testUpdatePortfolio() {
        User mockUser = new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55), LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55)));
        Portfolio mockPortfolio = new Portfolio(1L, "name", mockUser);

        when(portfolioRepository.findById(anyLong())).thenReturn(mockPortfolio);
        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(mockPortfolio);

        Portfolio result = portfolioServiceImpl.updatePortfolio(new UpdatePortfolioRequest("portfolioName", 1L));
        Assertions.assertEquals(mockPortfolio, result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme