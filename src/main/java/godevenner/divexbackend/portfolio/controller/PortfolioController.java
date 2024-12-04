package godevenner.divexbackend.portfolio.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.portfolio.dto.CreatePortfolioRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.dto.PortfolioResponse;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.service.PortfolioEntryService;
import godevenner.divexbackend.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody CreatePortfolioRequest createPortfolioRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        Portfolio portfolio = portfolioService.createPortfolio(createPortfolioRequest.portfolioName(), Long.parseLong(userId));
        return ResponseEntity.ok(portfolio);
    }



    @PreAuthorize("hasAuthority('PREMIUM') || hasAuthority('FREE')")
    @PostMapping("/portfolioentry")
    public ResponseEntity<PortfolioEntryResponse> createPortfolioEntry(@RequestBody PortfolioEntryRequest request) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + request);
        PortfolioEntryResponse response = portfolioEntryService.createPortfolioEntry(request);
        return ResponseEntity.ok(response);
    }

}
