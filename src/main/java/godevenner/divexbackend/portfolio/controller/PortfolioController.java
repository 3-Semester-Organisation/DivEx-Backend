package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.portfolio.dto.CreatePortfolioRequest;
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
    private final JwtService jwtService;

    @GetMapping("/portfolio")
    public ResponseEntity<List<Portfolio>> getPortfolios(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        List<Portfolio> portfolio = portfolioService.getPortfolios(Long.parseLong(userId));
        return ResponseEntity.ok(portfolio);
    }

    // this shit do be bussin respectfully
    @PostMapping("/portfolio")
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody CreatePortfolioRequest createPortfolioRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        Portfolio portfolio = portfolioService.createPortfolio(createPortfolioRequest.portfolioName(), Long.parseLong(userId));
        return ResponseEntity.ok(portfolio);
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
