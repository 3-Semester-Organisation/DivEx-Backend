package godevenner.divexbackend.stock.controller;

import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/stock/{ticker}")
    public ResponseEntity<StockResponse> getStock(@PathVariable String ticker) {
        StockResponse stockResponse = stockService.getStock(ticker);
        return ResponseEntity.ok(stockResponse);
    }
}
