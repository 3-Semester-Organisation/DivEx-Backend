package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.AccessType;
import godevenner.divexbackend.stock.model.Popularity;
import godevenner.divexbackend.stock.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockService {

    StockResponse getStock(String ticker);

    Page<StockResponse> getAllStocks(Pageable pageable);

    Popularity createPopularity(long userId, long stockId, AccessType accessType);
}
