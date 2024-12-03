package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {

    StockResponse getStockByTicker(String ticker);

    List<StockResponse> getStocksByNameOrTicker(String searchTerm);

    Page<StockResponse> getAllStocks(Pageable pageable);
}
