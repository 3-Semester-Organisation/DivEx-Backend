package godevenner.divexbackend.stock.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.stock.dto.PopularityResponse;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.AccessType;
import godevenner.divexbackend.stock.model.Popularity;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.service.StockService;
import godevenner.divexbackend.user.model.User;
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
    private final JwtService jwtService;


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

    @PostMapping("/stock/popularity")
    public ResponseEntity<Popularity> createPopularity(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody Long stockId,
                                                       @RequestBody AccessType accessType){

        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);

        Popularity popularity = stockService.createPopularity(
                Long.parseUnsignedLong(userId),
                stockId,accessType
        );

        return ResponseEntity.ok(popularity);
    }
}
