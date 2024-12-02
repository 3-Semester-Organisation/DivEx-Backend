package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.stock.PopularityComparator;
import godevenner.divexbackend.stock.dto.StockPopularity;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.StockRepository;
import godevenner.divexbackend.tracker.TrackLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularityServiceImpl implements PopularityService {

    private final StockRepository stockRepository;
    private final TrackLogRepository trackLogRepository;


    @Override
    public StockPopularity getPopularityByStockId(int stockId) {

        int stockVisits = trackLogRepository.countByStockId(stockId);

        StockPopularity stockPopularity = new StockPopularity(stockId, stockVisits);

        return stockPopularity;
    }

    @Override
    public List<StockPopularity> getAllStockPopularities() {

        List<StockPopularity> stockPopularities = new ArrayList<>();

        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            stockPopularities.add(
                    new StockPopularity(
                            stock.getId(),
                            trackLogRepository.countByStockId(stock.getId())
                    )
            );
        }

        return stockPopularities;
    }

    @Override
    public List<StockPopularity> getStocksInOrderOfPopularity() {
        List<StockPopularity> stockPopularities = getAllStockPopularities();

        Comparator<StockPopularity> popularityComparator = new PopularityComparator();

        stockPopularities.sort(popularityComparator);

        return stockPopularities;
    }
}
