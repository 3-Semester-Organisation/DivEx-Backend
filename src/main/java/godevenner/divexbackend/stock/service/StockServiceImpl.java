package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.exception.InvalidTickerException;
import godevenner.divexbackend.stock.dto.DividendDateResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.mapper.DividendDateResponseMapper;
import godevenner.divexbackend.stock.mapper.StockResponseMapper;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.DividendRepository;
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
    private final DividendRepository dividendRepository;
    private final DividendDateResponseMapper dividendDateResponseMapper;


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
    public List<DividendDateResponse> getDividendDates() {
        return dividendRepository.findAll().stream().map(dividendDateResponseMapper::toDividendDateResponse).toList();
    }

    @Override
    public Page<StockResponse> getAllStocksByDate(Long date, Pageable pageable) {
        return stockRepository.findAllByDividend_ExDividendDate(date, pageable).map(stockResponseMapper::toStockResponse);
    }


}
