package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.DividendDateResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {

    StockResponse getStockByTicker(String ticker);

    List<StockResponse> getStocksByNameOrTicker(String searchTerm);

    Page<StockResponse> getAllStocks(Pageable pageable);

    List<DividendDateResponse> getDividendDates();

    Page<StockResponse> getAllStocksByDate(Long date, Pageable pageable);
}
