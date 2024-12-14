package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.exception.MaximumPortfolioEntriesReachException;
import godevenner.divexbackend.portfolio.dto.*;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.service.PortfolioEntryService;
import godevenner.divexbackend.portfolio.service.PortfolioService;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<PortfolioResponse>> getPortfolios(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        List<PortfolioResponse> response = portfolioService.getPortfolios(Long.parseLong(userId));
        return ResponseEntity.ok(response);
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

    @PutMapping("/portfolio/goal")
    public ResponseEntity<PortfolioResponse> updatePortfolioGoal(@RequestBody UpdatePortfolioGoalRequest request) {
        PortfolioResponse portfolio = portfolioService.updatePortfolioGoal(request);
        return ResponseEntity.ok(portfolio);
    }

    @DeleteMapping("/portfolio")
    public ResponseEntity<Void> deletePortfolio(@RequestBody DeletePortfolioRequest request) {
        portfolioService.deletePortfolio(request);
        return ResponseEntity.noContent().build();
    }



    @PreAuthorize("hasAuthority('PREMIUM') || hasAuthority('FREE')")
    @PostMapping("/portfolioentry")
    public ResponseEntity<PortfolioEntryResponse> createPortfolioEntry(@RequestBody PortfolioEntryRequest request, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtService.extractUserId(token));

        Long portfolioId = request.portfolioId();
        if (userService.maxNumberOfPortfolioEntriesPrPortfolioReached(portfolioId, userId)) {
            throw new MaximumPortfolioEntriesReachException("Cannot add entry. Free membership entry limit reached.");
        }

        PortfolioEntryResponse response = portfolioEntryService.createPortfolioEntry(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/portfolioentry")
    public ResponseEntity<Void> deletePortfolioEntry(@RequestBody DeletePortfolioEntryRequest request) {
        portfolioEntryService.deletePortfolioEntry(request);
        return ResponseEntity.noContent().build();
    }
}
