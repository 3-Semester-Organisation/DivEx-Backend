//package godevenner.divexbackend.portfolio.service;
//
//import godevenner.divexbackend.portfolio.dto.PortfolioResponse;
//import godevenner.divexbackend.portfolio.dto.UpdatePortfolioRequest;
//import godevenner.divexbackend.portfolio.model.Portfolio;
//import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
//import godevenner.divexbackend.subscription.Subscription;
//import godevenner.divexbackend.subscription.SubscriptionType;
//import godevenner.divexbackend.user.model.User;
//import godevenner.divexbackend.user.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//class PortfolioServiceImplTest {
//    @Mock
//    PortfolioRepository portfolioRepository;
//    @InjectMocks
//    PortfolioServiceImpl portfolioServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetPortfolios() {
//        // Mocking user and subscription behavior using builder pattern
//        Subscription mockSubscription = Subscription.builder()
//                .id(1L)
//                .isSubscribed(true)
//                .subscriptionType(SubscriptionType.PREMIUM)
//                .lastPayment(LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55))
//                .nextPayment(LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55))
//                .build();
//
//        User mockUser = User.builder()
//                .id(1L)
//                .username("username")
//                .email("email")
//                .password("password")
//                .firstName("firstName")
//                .lastName("lastName")
//                .phone("phone")
//                .address("address")
//                .city("city")
//                .subscription(mockSubscription)
//                .build();
//
//        Portfolio mockPortfolio = Portfolio.builder()
//                .id(1L)
//                .name("name")
//                .user(mockUser)
//                .portfolioEntries(List.of()) // Assuming no entries in this mock portfolio
//                .build();
//
//        // Mocking the repository call
//        when(portfolioRepository.findByUserId(1L)).thenReturn(new ArrayList(List.of(mockPortfolio)));
//
//        // Call the method and assert the response
//        List<PortfolioResponse> result = portfolioServiceImpl.getPortfolios(1L);
//
//        // Verify that the mock portfolio is correctly returned
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals("name", result.get(0).name());
//    }
//
//
//    @Test
//    void testUpdatePortfolio() {
//        User mockUser = new User(Long.valueOf(1), "username", "email", "password", "firstName", "lastName", "phone", "address", "city", new Subscription(Long.valueOf(1), true, SubscriptionType.PREMIUM, LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55), LocalDateTime.of(2024, Month.DECEMBER, 3, 20, 3, 55)));
//        Portfolio mockPortfolio = Portfolio.builder()
//                .id(1L)
//                .name("name")
//                .user(mockUser)
//                .build();
//
//        when(portfolioRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockPortfolio));
//        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(mockPortfolio);
//
//        Portfolio result = portfolioServiceImpl.updatePortfolio(new UpdatePortfolioRequest("portfolioName", 1L));
//        Assertions.assertEquals(mockPortfolio, result);
//    }
//}
//
////Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme