package godevenner.divexbackend.portfolio.dto;

public record DeletePortfolioRequest (
        long portfolioId,
        String portfolioName
){
}
