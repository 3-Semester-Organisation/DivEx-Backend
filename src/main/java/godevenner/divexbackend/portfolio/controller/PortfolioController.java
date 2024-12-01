package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.service.PortfolioEntryService;
import godevenner.divexbackend.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final PortfolioEntryService portfolioEntryService;

    @GetMapping("/portfolio")
    public ResponseEntity<List<Portfolio>> getPortfolios(@RequestBody Integer userId) {
        List<Portfolio> portfolio = portfolioService.getPortfolios(userId);
        return ResponseEntity.ok(portfolio);
    }

    @PostMapping("/portfolio")
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolio) {
        Portfolio createdPortfolio = portfolioService.createPortfolio(portfolio);
        return ResponseEntity.ok(createdPortfolio);
    }

    @GetMapping("/portfolioentries")
    public ResponseEntity<List<PortfolioEntry>> getPortfolioEntries(@RequestBody Integer userId) {
        List<PortfolioEntry> portfolioentries = portfolioEntryService.getPortfolioEntries(userId);
        return ResponseEntity.ok(portfolioentries);
    }

    @PostMapping("/portfolioentry")
    public ResponseEntity<PortfolioEntry> createPortfolioEntry(@RequestBody PortfolioEntry portfolioEntry) {
        PortfolioEntry createdPortfolioEntry = portfolioEntryService.createPortfolioEntry(portfolioEntry);
        return ResponseEntity.ok(createdPortfolioEntry);
    }

}
