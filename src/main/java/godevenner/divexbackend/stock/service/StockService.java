package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.Stock;

public interface StockService {

    StockResponse getStock(String ticker);
}
