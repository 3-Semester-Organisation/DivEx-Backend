package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockPopularity;

import java.util.List;

public interface PopularityService {

    StockPopularity getPopularityByStockId(long stockId);

    List<StockPopularity> getAllStockPopularities();
    List<StockPopularity> getAllStockPopularitiesForWeek();
    List<StockPopularity> getAllStockPopularitiesForMonth();

    List<StockPopularity> getStocksInOrderOfPopularity();
}
