package godevenner.divexbackend.stock.controller;

import godevenner.divexbackend.stock.dto.StockPopularity;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.service.PopularityService;
import godevenner.divexbackend.stock.service.StockService;
import godevenner.divexbackend.tracker.TrackerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class StockController {

    private final StockService stockService;
    private final TrackerService trackerService;
    private final PopularityService popularityService;


    @GetMapping("/stocks")
    public ResponseEntity<Page<StockResponse>> getAllStocks(Pageable pageable) {
        Page<StockResponse> response = stockService.getAllStocks(pageable);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/stock/{ticker}")
    public ResponseEntity<StockResponse> getStock(@PathVariable String ticker, HttpServletRequest request) {
        StockResponse stockResponse = stockService.getStock(ticker);
        String ipAddress = request.getRemoteAddr();
        trackerService.track(stockResponse.stockId(),ipAddress);
        return ResponseEntity.ok(stockResponse);
    }

    @GetMapping("/stock/popularity/week")
    public ResponseEntity<List<StockPopularity>> getStockPopularityWeek(){
        List<StockPopularity> weeklyStockPopularities = popularityService.getAllStockPopularitiesForWeek();
        return ResponseEntity.ok(weeklyStockPopularities);
    }

    @GetMapping("/stock/popularity/month")
    public ResponseEntity<List<StockPopularity>> getStockPopularityMonth(){
        List<StockPopularity> monthlyStockPopularities = popularityService.getAllStockPopularitiesForMonth();

        return ResponseEntity.ok(monthlyStockPopularities);
    }

    @GetMapping("/stock/popularity/all")
    public ResponseEntity<List<StockPopularity>> getAllStockPopularities(){
        List<StockPopularity> allStockPopularities = popularityService.getAllStockPopularities();

        return ResponseEntity.ok(allStockPopularities);
    }

    @GetMapping("/stock/{id}/popularity")
    public ResponseEntity<StockPopularity> getAllStockPopularityByStockId(@PathVariable String id){
        StockPopularity stockPopularityByStockId = popularityService.
                getPopularityByStockId(Integer.parseInt(id));

        return ResponseEntity.ok(stockPopularityByStockId);
    }
}
