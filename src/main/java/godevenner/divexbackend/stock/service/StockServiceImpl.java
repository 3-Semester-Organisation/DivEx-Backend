package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.exception.InvalidTickerException;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.mapper.StockResponseMapper;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockResponseMapper stockResponseMapper;


    @Override
    public List<StockResponse> getStocksByNameOrTicker(String searchTerm) {
        return stockRepository.findByNameOrTicker(searchTerm).stream()
                .map(stockResponseMapper::toStockResponse)
                .toList();
    }

    @Override
    public Page<StockResponse> getAllStocks(Pageable pageable) {
        return stockRepository.findAll(pageable).map(stockResponseMapper::toStockResponse);
    }


    @Override
    public StockResponse getStockByTicker(String ticker) {
        Stock stock = stockRepository.findByTicker(ticker).orElseThrow( () -> new InvalidTickerException("Invalid ticker: " + ticker));
        StockResponse response = stockResponseMapper.toStockResponse(stock);
        return response;
    }


}
