package godevenner.divexbackend.stock.service;

import godevenner.divexbackend.exception.InvalidStockException;
import godevenner.divexbackend.stock.PopularityComparator;
import godevenner.divexbackend.stock.dto.StockPopularity;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.StockRepository;
import godevenner.divexbackend.tracker.TrackLog;
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

        Stock stock = stockRepository.findById(stockId).orElseThrow(
                () -> new InvalidStockException(
                        "Invalid Stock: "+stockRepository.findById(stockId))
        );

        int stockVisits = trackLogRepository.countByStockId(stockId);

        StockPopularity stockPopularity = new StockPopularity(
                stockId,
                stock.getName(),
                stock.getTicker(),
                stockVisits);

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
                            stock.getName(),
                            stock.getTicker(),
                            trackLogRepository.countByStockId(stock.getId())
                    )
            );
        }

        //sorting (should probably be changed later
        Comparator<StockPopularity> popularityComparator = new PopularityComparator();

        stockPopularities.sort(popularityComparator);

        return stockPopularities;
    }

    @Override
    public List<StockPopularity> getAllStockPopularitiesForWeek() {

        List<Stock> stocks = stockRepository.findAll();

        long weekInSeconds = 24 * 7 * 60 * 60; //604.800 seconds

        List<StockPopularity> stockPopularities = filterStocksByPeriod(stocks,weekInSeconds);

        //sorting (should probably be changed later
        Comparator<StockPopularity> popularityComparator = new PopularityComparator();

        stockPopularities.sort(popularityComparator);

        return stockPopularities;
    }

    @Override
    public List<StockPopularity> getAllStockPopularitiesForMonth() {

        List<Stock> stocks = stockRepository.findAll();

        long monthInSeconds = 24 * 30 * 60 * 60; //2.592.000 seconds

        List<StockPopularity> stockPopularities = filterStocksByPeriod(stocks,monthInSeconds);

        //sorting (should probably be changed later
        Comparator<StockPopularity> popularityComparator = new PopularityComparator();

        stockPopularities.sort(popularityComparator);

        return stockPopularities;
    }

    @Override
    public List<StockPopularity> getStocksInOrderOfPopularity() {
        List<StockPopularity> stockPopularities = getAllStockPopularities();

        Comparator<StockPopularity> popularityComparator = new PopularityComparator();

        stockPopularities.sort(popularityComparator);

        return stockPopularities;
    }

    private List<StockPopularity> filterStocksByPeriod(List<Stock> stocks, long period){

        long currentTime = System.currentTimeMillis()/1000;
        List<StockPopularity> stockPopularities = new ArrayList<>();

        for (Stock stock : stocks) {
            for(TrackLog tracklog : trackLogRepository.findAllByStockId(stock.getId())) {
                if (currentTime - period > tracklog.getTimestamp()) {
                    stockPopularities.add(
                            new StockPopularity(
                                    stock.getId(),
                                    stock.getName(),
                                    stock.getTicker(),
                                    trackLogRepository.countByStockId(stock.getId())
                            )
                    );
                }
            }
        }
        return stockPopularities;
    }
}
