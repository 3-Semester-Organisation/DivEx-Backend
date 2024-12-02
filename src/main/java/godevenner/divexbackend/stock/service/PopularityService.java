package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockPopularity;

import java.util.List;

public interface PopularityService {

    StockPopularity getPopularityByStockId(int stockId);

    List<StockPopularity> getAllStockPopularities();

    List<StockPopularity>
}
