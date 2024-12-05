package godevenner.divexbackend.stock.controller;

import godevenner.divexbackend.stock.dto.DividendDateResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.service.StockService;
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


    @GetMapping("/stocks")
    public ResponseEntity<Page<StockResponse>> getAllStocks(Pageable pageable) {
        Page<StockResponse> response = stockService.getAllStocks(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stocksByDate")
    public ResponseEntity<Page<StockResponse>> getAllStocksByDate(@RequestParam Long date, Pageable pageable) {
        System.out.println("date: " + date);
        Page<StockResponse> response = stockService.getAllStocksByDate(date, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stock/{ticker}")
    public ResponseEntity<StockResponse> getStockByTicker(@PathVariable String ticker) {
        StockResponse stockResponse = stockService.getStockByTicker(ticker);
        return ResponseEntity.ok(stockResponse);
    }


    @GetMapping("/stocks/{searchTerm}")
    public ResponseEntity<List<StockResponse>> getStockBySearchTerm(@PathVariable String searchTerm) {
        List<StockResponse> stockResponse = stockService.getStocksByNameOrTicker(searchTerm);
        return ResponseEntity.ok(stockResponse);
    }

    @GetMapping("/stocks/dividendDates")
    public ResponseEntity<List<DividendDateResponse>> getDividendDates() {
        List<DividendDateResponse> response = stockService.getDividendDates();
        return ResponseEntity.ok(response);
    }
}
