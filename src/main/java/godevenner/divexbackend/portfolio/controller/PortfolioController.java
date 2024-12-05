package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.portfolio.dto.CreatePortfolioRequest;
import godevenner.divexbackend.portfolio.dto.UpdatePortfolioRequest;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.portfolio.service.PortfolioEntryService;
import godevenner.divexbackend.portfolio.service.PortfolioService;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final PortfolioEntryService portfolioEntryService;
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/portfolio")
    public ResponseEntity<List<Portfolio>> getPortfolios(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtService.extractUserId(token));
        List<Portfolio> portfolio = portfolioService.getPortfolios(userId);
        return ResponseEntity.ok(portfolio);
    }

    // this shit do be bussin respectfully
    @PostMapping("/portfolio")
    public ResponseEntity createPortfolio(@RequestBody CreatePortfolioRequest createPortfolioRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtService.extractUserId(token));

        if (userService.maxNumberOfPortfoliosPrUserReached(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot add portfolio. Free membership portfolio limit reached.");
        }

        Portfolio portfolio = portfolioService.createPortfolio(createPortfolioRequest.portfolioName(), userId);
        return ResponseEntity.ok(portfolio);
    }

    @PutMapping("/portfolio")
    public ResponseEntity<Portfolio> updatePortfolio(@RequestBody UpdatePortfolioRequest request) {
        Portfolio portfolio = portfolioService.updatePortfolio(request);
        return ResponseEntity.ok(portfolio);
    }

    @GetMapping("/portfolioentries")
    public ResponseEntity<List<PortfolioEntry>> getPortfolioEntries(@RequestBody Long userId) {
        List<PortfolioEntry> portfolioentries = portfolioEntryService.getPortfolioEntries(userId);
        return ResponseEntity.ok(portfolioentries);
    }

    @PostMapping("/portfolioentry")
    public ResponseEntity createPortfolioEntry(@RequestBody PortfolioEntry portfolioEntry, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtService.extractUserId(token));

        Long portfolioId = portfolioEntry.getPortfolio().getId();
        if (userService.maxNumberOfPortfolioEntriesPrPortfolioReached(portfolioId, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot add entry. Free membership entry limit reached.");
        }

        PortfolioEntry createdPortfolioEntry = portfolioEntryService.createPortfolioEntry(portfolioEntry);
        return ResponseEntity.ok(createdPortfolioEntry);
    }
}
