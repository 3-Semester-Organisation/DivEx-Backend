package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockService {

    StockResponse getStock(String ticker);

    Page<StockResponse> getAllStocks(Pageable pageable);
}
