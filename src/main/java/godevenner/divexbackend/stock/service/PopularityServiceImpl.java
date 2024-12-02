package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.dto.StockPopularity;
import godevenner.divexbackend.stock.repository.StockRepository;
import godevenner.divexbackend.tracker.TrackLog;
import godevenner.divexbackend.tracker.TrackLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularityServiceImpl implements PopularityService {

    private final TrackLogRepository trackLogRepository;


    @Override
    public StockPopularity getPopularityByStockId(int stockId) {

        List<TrackLog> logs = trackLogRepository.findAllByStockId(stockId);
        int stockVisits = logs.size();

        StockPopularity stockPopularity = new StockPopularity(stockId, stockVisits);

        return stockPopularity;
    }

    @Override
    public List<StockPopularity> getAllStockPopularities() {
        return List.of();
    }

    @Override
    public List<StockPopularity> getStocksInOrderOfPopularity() {
        return List.of();
    }
}
