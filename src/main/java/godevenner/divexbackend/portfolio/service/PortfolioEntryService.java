package godevenner.divexbackend.portfolio.service;


import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;

import java.util.List;

public interface PortfolioEntryService {
    PortfolioEntryResponse createPortfolioEntry(PortfolioEntryRequest request);
    void deletePortfolioEntry(String portfolioEntryTicker);
}
