package godevenner.divexbackend.portfolio.service;


import godevenner.divexbackend.portfolio.dto.DeletePortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;

public interface PortfolioEntryService {
    PortfolioEntryResponse createPortfolioEntry(PortfolioEntryRequest request);
    void deletePortfolioEntry(DeletePortfolioEntryRequest deletePortfolioEntryRequest);
}
