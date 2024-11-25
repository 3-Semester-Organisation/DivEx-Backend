package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.exception.InvalidTickerException;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.mapper.StockResponseMapper;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockResponseMapper stockResponseMapper;

    @Override
    public StockResponse getStock(String ticker) {
        Stock stock = stockRepository.findByTicker(ticker).orElseThrow( () -> new InvalidTickerException("Invalid ticker: " + ticker));
        StockResponse response = stockResponseMapper.toStockResponse(stock);
        return response;
    }


}
