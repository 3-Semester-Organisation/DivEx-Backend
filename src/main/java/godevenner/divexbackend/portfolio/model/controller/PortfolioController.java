package godevenner.divexbackend.portfolio.model.controller;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/portfolio")
    public ResponseEntity<List<Portfolio>> getPortfolios(@RequestBody Integer userId) {


        List<Portfolio> portfolio = portfolioService.getPortfolios(userId);
        return ResponseEntity.ok(portfolio);
    }


}
