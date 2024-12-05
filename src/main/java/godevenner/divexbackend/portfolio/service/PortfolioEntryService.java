package godevenner.divexbackend.portfolio.service;


import godevenner.divexbackend.portfolio.model.PortfolioEntry;

import java.util.List;

public interface PortfolioEntryService {

    List<PortfolioEntry> getPortfolioEntries(Long portfolioId);
    PortfolioEntry createPortfolioEntry(PortfolioEntry portfolioEntry);

}
