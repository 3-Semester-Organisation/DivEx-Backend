package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.exception.InvalidTickerException;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.mapper.StockResponseMapper;
import godevenner.divexbackend.stock.model.AccessType;
import godevenner.divexbackend.stock.model.Popularity;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.StockRepository;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockResponseMapper stockResponseMapper;
    private final UserRepository userRepository;


    @Override
    public Page<StockResponse> getAllStocks(Pageable pageable) {
        return stockRepository.findAll(pageable).map(stockResponseMapper::toStockResponse);
    }


    @Override
    public StockResponse getStock(String ticker) {
        Stock stock = stockRepository.findByTicker(ticker).orElseThrow( () -> new InvalidTickerException("Invalid ticker: " + ticker));
        StockResponse response = stockResponseMapper.toStockResponse(stock);
        return response;
    }


    @Override
    public Popularity createPopularity(long userId, long stockId, AccessType accessType) {
        Popularity popularity = new Popularity();

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        popularity.setAccessDate(Long.parseLong(LocalDateTime.now().toString()));
        popularity.setUser(user.get());

        return popularity;
    }
}
